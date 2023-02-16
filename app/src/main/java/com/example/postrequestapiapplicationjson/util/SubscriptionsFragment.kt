package com.example.postrequestapiapplicationjson.util

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.postrequestapiapplicationjson.adapter.BlogAdapter
import com.example.postrequestapiapplicationjson.databinding.FragmentSubscriptionsBinding
import com.example.postrequestapiapplicationjson.model.Placeholder
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException


class SubscriptionsFragment : Fragment() {

    lateinit var blogAdapter: BlogAdapter
    var lm = LinearLayoutManager(activity)
    private var _binding: FragmentSubscriptionsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubscriptionsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        getData()
    }

    fun initView() {
        binding.rvBlog.layoutManager = lm
        blogAdapter = BlogAdapter(requireActivity())
        binding.rvBlog.adapter = blogAdapter

    }

    private fun getData() {
        val request = Request.Builder()
            .url("https://jsonplaceholder.typicode.com/posts")
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object: Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.e("Failed", e.message!!)
            }

            override fun onResponse(call: Call, response: Response) {

                val body = response.body?.string()
                // convert body ke object
                val gson = GsonBuilder().create()
                val result = gson.fromJson(body, Array<Placeholder>::class.java).toList()

                activity?.runOnUiThread {
                    binding.tvSubscription.text = result.get(0).body!!
                    blogAdapter.setBlog(result)
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}