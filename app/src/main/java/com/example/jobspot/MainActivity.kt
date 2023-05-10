package com.example.jobspot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.jobspot.jobPost.AddJobActivity
import com.example.jobspot.jobPost.FetchingActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goFeedBack(view: View) {
        val intent = Intent(this,FeedBackMain::class.java)
        startActivity(intent)
    }

    fun addJobView(view: View) {
        val intent = Intent(this, AddJobActivity::class.java)
        startActivity(intent)
    }

    fun searchJobs(view: View) {
        val intent = Intent(this, FetchingActivity::class.java)
        startActivity(intent)
    }
}