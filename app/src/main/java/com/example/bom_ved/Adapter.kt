package com.example.bom_ved

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.bom_ved.databinding.ItemRecyclerviewBinding
import java.io.InterruptedIOException
import java.util.concurrent.TimeUnit
import kotlin.reflect.KFunction2

class Adapter(): RecyclerView.Adapter<Adapter.Holder>() {
    // В clickItem передаем showSnackbar() из FragmentViewHolder

    // Добавленные переменные
    var listItem: List<String> = listOf()
    private var callbacks: TaskCallbacks? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val text = listItem[position]
        holder.bind(text)
    }

    override fun getItemCount(): Int {
        return listItem.size
    }

    inner class Holder internal constructor(private val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(text: String) = binding.run{
            textView.text = text
        }
    }

//    inner class MyTask : AsyncTask<Unit, Int, Unit>() {
//
//        override fun onPreExecute() {
//            callbacks?.onPreExecuted()
//        }
//
//        override fun doInBackground(vararg params: Unit?) {
//            Log.d("My_Tag", "lol23")
//            try {
//                for (i in 0..3) {
//                    TimeUnit.SECONDS.sleep(1)
//                    if (isCancelled) break
//                }
//            } catch (e: InterruptedIOException) {
//                e.printStackTrace()
//            }
//        }
//
//        override fun onPostExecute(result: Unit?) {
//            callbacks?.let {
//                handler?.sendEmptyMessage(1)
//                handler?.sendEmptyMessageDelayed(2, 2000)
//                handler?.sendEmptyMessageDelayed(3, 4000)
//                handler?.sendEmptyMessageDelayed(4, 6000)
//                handler?.sendEmptyMessageDelayed(5, 8000)
//            }
//        }
//    }

}