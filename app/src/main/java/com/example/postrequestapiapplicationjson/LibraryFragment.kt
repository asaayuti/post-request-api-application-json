package com.example.postrequestapiapplicationjson

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.postrequestapiapplicationjson.databinding.FragmentLibraryBinding
import com.example.postrequestapiapplicationjson.model.Comment
import com.example.postrequestapiapplicationjson.model.user.UserRequest
import com.example.postrequestapiapplicationjson.model.user.UserResponse
import com.example.postrequestapiapplicationjson.service.CommentApi
import com.example.postrequestapiapplicationjson.service.UserApi
import com.example.postrequestapiapplicationjson.util.Retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCommentApi()
        initAction()
    }

    private fun initAction() {
        binding.btnCreate.setOnClickListener {
            postApi()
        }
    }

    private fun getCommentApi() {
        val retro = Retro().getRetroClientInstance("https://jsonplaceholder.typicode.com/").create(CommentApi::class.java)
        retro.getComments().enqueue(object: Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                val comment = response.body()
                for (c in comment!!) {
//                    Log.e("Hasil", c.email)
                    c.email?.let { Log.e("Hasil", it) }
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.e("Failed", t.message.toString())
            }

        })
    }

    private fun postApi() {
        val userReq = UserRequest()
        userReq.name = binding.etName.text.toString()
        userReq.job = binding.etJob.text.toString()

        val retro = Retro().getRetroClientInstance("https://reqres.in/").create(UserApi::class.java)
        retro.createUser(userReq).enqueue(object: Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                val user = response.body()
                binding.apply {
                    tvResultName.text = user!!.name
                    tvResultJob.text = user.job
                    tvResultId.text = user.id
                    tvResultCreatedAt.text = user.createdAt
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.e("Failed", t.message.toString())
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}