package com.example.postrequestapiapplicationjson

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.postrequestapiapplicationjson.model.Comment
import com.example.postrequestapiapplicationjson.service.CommentApi
import com.example.postrequestapiapplicationjson.util.Retro
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LibraryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCommentApi()
    }

    private fun getCommentApi() {
        val retro = Retro().getRetroClientInstance().create(CommentApi::class.java)
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

}