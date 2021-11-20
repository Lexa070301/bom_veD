package com.example.bom_ved

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bom_ved.databinding.ActivityMainBinding
import java.io.InterruptedIOException
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), TaskCallbacks {
    private lateinit var binding: ActivityMainBinding
    private val fragment = Adapter()
    private var myResult: Int = 0
    var adapter: Adapter = Adapter()

    private var handler: Handler? = null
    private var callbacks: TaskCallbacks? = null
    private var myTask: MyAsyncTask? = null

    private var listItem:MutableList<String> = mutableListOf()

    companion object {
        const val RESULT = "RESULT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        savedInstanceState?.getInt(RESULT).let {
            showProgress()
        }

        setupRecycleView()
        adapter.listItem = listItem
    }

    override fun onStart() {
        super.onStart()
        callbacks = this
        startTask()
    }

    // Подключение и настройка recycleView
    private fun setupRecycleView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }

    private fun showProgress(){

    }

    private fun startTask() {
        myTask = MyAsyncTask()
        val callback = object : Handler.Callback {
            override fun handleMessage(msg: Message): Boolean {
                callbacks?.onPostExecute(msg.toString())
                return false
            }
        }
        handler = Handler(callback)
        myTask!!.execute()
    }

    private fun cancelTask(){
        callbacks = null
    }

    @SuppressLint("StaticFieldLeak")
    inner class MyAsyncTask : AsyncTask<Unit, Int, Unit>() {
        override fun onPreExecute() {
            callbacks?.onPreExecuted()
        }

        override fun doInBackground(vararg params: Unit?) {
            Log.d("Started", "I'm Started")
            try {
                for (i in 0..3) {
                    TimeUnit.SECONDS.sleep(1)
                    if (isCancelled) break
                }
            } catch (e: InterruptedIOException) {
                e.printStackTrace()
            }
        }

        override fun onPostExecute(result: Unit?) {
            callbacks?.let {
                for (i in 1..100){
                    handler?.sendEmptyMessageDelayed(i, ((i-1)*500).toLong())
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(RESULT, myResult)
    }

    override fun onBackPressed() {
        Log.d("cancel", "back Pressed")
        cancelTask()
    }

    override fun onPreExecuted() {
        Log.d("cancel", "executed")
    }

    override fun onCancelled() {
        Log.d("cancel", "cancel")
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onPostExecute(i: String) {
        listItem.add(i)
        adapter.listItem = listItem
        adapter.notifyDataSetChanged()

        Log.d("MESSAGE", i)
    }
}

interface TaskCallbacks {
    fun onPreExecuted()
    fun onCancelled()
    fun onPostExecute(i: String)
}