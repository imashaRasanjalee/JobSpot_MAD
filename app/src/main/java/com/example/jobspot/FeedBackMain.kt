package com.example.jobspot

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.jobspot.feedBack.AddFeedBackActivity
import com.example.jobspot.feedBack.FetchingActivity

class FeedBackMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_feedback)
    }



    fun getAddFeedBackView(view: View) {
        val intent = Intent(this, AddFeedBackActivity::class.java)
        startActivity(intent)
    }
    fun getFeedBackListView(view: View) {
        val intent = Intent(this, FetchingActivity::class.java)
        startActivity(intent)
    }
}