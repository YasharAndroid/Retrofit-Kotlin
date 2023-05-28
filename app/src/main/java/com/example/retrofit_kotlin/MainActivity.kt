package com.example.retrofit_kotlin

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit_kotlin.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val baseURL = "https://jsonplaceholder.typicode.com/"

    lateinit var mainBinding: ActivityMainBinding

    var postList = ArrayList<Posts>()

    lateinit var adapter: PostsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * for using view binding created in gradle : buildFeatures
         */
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root

        setContentView(view)

        mainBinding.recyclerView.layoutManager = LinearLayoutManager(this)

        showPosts()
    }

    fun showPosts() {
        val retrofit = Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI: RetrofitAPI = retrofit.create(RetrofitAPI::class.java)

        val call: Call<List<Posts>> = retrofitAPI.getAllPost()

        call.enqueue(object : Callback<List<Posts>> {
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {

                if (response.isSuccessful) {

                    mainBinding.progressBar.isVisible = false
                    mainBinding.recyclerView.isVisible = true

                    postList = response.body() as ArrayList<Posts>

                    adapter = PostsAdapter(postList)

                    mainBinding.recyclerView.adapter = adapter
                }

            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {

                Toast.makeText(applicationContext, t.localizedMessage, Toast.LENGTH_LONG).show()
            }

        })
    }
}