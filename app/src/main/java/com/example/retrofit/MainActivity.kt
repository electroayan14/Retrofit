package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

const val BASE_URL="https://jsonplaceholder.typicode.com/"
class MainActivity : AppCompatActivity() {

    lateinit var adapter: RecylerAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view= binding.root
        setContentView(view)

        binding.recyclerView.setHasFixedSize(true)
        linearLayoutManager= LinearLayoutManager(this)
        binding.recyclerView.layoutManager= linearLayoutManager

        fetchMydata()
    }

    private fun fetchMydata(){
        val retrofitbuilder= Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
            .create(ApiInterface::class.java)


        val retrofitData= retrofitbuilder.getdata()

        retrofitData.enqueue(object : Callback<List<DataItemModel>?>{
            override fun equals(other: Any?): Boolean {
                return super.equals(other)
            }

            override fun onResponse(
                call: Call<List<DataItemModel>?>,
                response: Response<List<DataItemModel>?>,
            ) {
                val responsebody = response.body()!!

                adapter = RecylerAdapter(baseContext, responsebody)
                adapter.notifyDataSetChanged()
                binding.recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<List<DataItemModel>?>, t: Throwable) {
                Toast.makeText(this@MainActivity,"${t.message}",Toast.LENGTH_SHORT).show()
            }

        })

    }
}