package com.example.bom_ved

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bom_ved.databinding.FrameAsyncTaskBinding
import com.google.android.material.snackbar.Snackbar
import java.io.InterruptedIOException
import java.util.concurrent.TimeUnit


class FragmentViewHolder: Fragment() {
    private lateinit var binding: FrameAsyncTaskBinding
    // Добавленные переменные
    private var myTask: MainActivity.MyAsyncTask? = null
//    var adapter: Adapter = Adapter( this::showSnackbar)
    private var callbacks: TaskCallbacks? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FrameAsyncTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // иницилизация recycleView
        setupRecycleView()
    }

    // Подключение и настройка recycleView
    private fun setupRecycleView(){
        binding.recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
//        binding.recyclerView.adapter = adapter
    }

    // Обработка нажатий на карточки в ViewHolder
//    private fun showSnackbar(picture: Picture, trigger: String): Unit{
//        var text: String = "Произошла ошибка"
//        when (trigger){
//            "itemInfo" -> text = "Нажата карточка: " + picture.Name
//            "like" -> text = "Нажат лайк: " + picture.Name
//            "details" -> {
//                val activityCallBack = requireActivity() as ActivityCallBack
//                activityCallBack.showDetails(picture)
//                return
//            }
//        }
//        Snackbar.make(binding.root, text, 3000).show()
//    }

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
}