package com.example.bom_ved

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.bom_ved.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val lastFragmentAsyncTask =
            supportFragmentManager.findFragmentByTag(FragmentAsyncTask.MyTag)

        if (lastFragmentAsyncTask == null) {
            val transactionInitialization = supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, FragmentAsyncTask(), FragmentAsyncTask.MyTag)
                .addToBackStack("added fragment")
            transactionInitialization.commit()
        } else {
            Log.d("fragment", "fragment is already exists")
        }
    }
}

interface TaskCallbacks {
    fun onPreExecuted()
    fun onCancelled()
    fun onPostExecute(i: String)
}