package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.fragment.InfoFragment
import com.example.myapplication.model.Response
import com.example.myapplication.model.Result
import com.example.myapplication.recycler.CharacterAdapter
import com.example.myapplication.recycler.CharacterViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Response as rfResponse


class MainActivity : AppCompatActivity(), CharacterViewHolder.Listener {

    private lateinit var context: Context
    private val characters = ArrayList<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        runBlocking {
            query()
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCharacterClicked(position: Int) {
        val characterInfo = characters[position]

        val bundle = Bundle()
        bundle.putString("gender", characterInfo.gender)
        bundle.putString("species", characterInfo.species)
        bundle.putString("type", characterInfo.type)
        bundle.putString("status", characterInfo.status)

        val fragobj = InfoFragment()

        Log.i("MainActivity", "bundle = $bundle")

        fragobj.arguments = bundle

        this.supportFragmentManager.beginTransaction()
                .replace(R.id.main, fragobj)
                .addToBackStack(null)
                .commit()
    }

    private suspend fun enqueue(callback: Call<Response?>?){
        callback?.enqueue(
                object : Callback<Response?> {
                    override fun onFailure(call: Call<Response?>?, t: Throwable) {
                        t.printStackTrace()
                        Log.i("MainActivity", "OnFailure")

                    }

                    override fun onResponse(
                            call: Call<Response?>,
                            response: rfResponse<Response?>) {

                        GlobalScope.launch(Dispatchers.IO)
                        {
                            val postlist: List<Result> = response.body()?.results as List<Result>
                            characters.addAll(postlist)

                            val adapter = CharacterAdapter(postlist, this@MainActivity)

                            withContext(Dispatchers.Main)
                            {
                                recyclerView.adapter = adapter
                                Log.i("GlobalScope", "Context")
                            }
                        }
                    }
                })
    }

    private suspend fun query() {

        GlobalScope.launch(Dispatchers.IO)
        {
            val rf = Retrofit.Builder()
                    .baseUrl(RetrofitInterface.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val API: RetrofitInterface = rf.create(RetrofitInterface::class.java)
            val call = API.posts

            enqueue(call)
        }
    }
}