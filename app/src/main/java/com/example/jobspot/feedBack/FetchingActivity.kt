package com.example.jobspot.feedBack

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobspot.R
import com.google.firebase.database.*

class FetchingActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvLoadingData: TextView
    private lateinit var arrayList: ArrayList<FeedBackModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_list)

        recyclerView = findViewById(R.id.rvCom)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        arrayList = arrayListOf<FeedBackModel>()

        getData()

    }

    private fun getData() {

        recyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("feedBacks")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                arrayList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val data = empSnap.getValue(FeedBackModel::class.java)
                        arrayList.add(data!!)
                    }
                    val mAdapter = FeedBackHelper(arrayList)
                    recyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : FeedBackHelper.onItemClickListener{
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@FetchingActivity, UpdateFeedActivity::class.java)
                            intent.putExtra("id", arrayList[position].id)
                            intent.putExtra("title", arrayList[position].title)
                            intent.putExtra("name", arrayList[position].name)
                            intent.putExtra("email", arrayList[position].email)
                            intent.putExtra("mobile", arrayList[position].phone)
                            intent.putExtra("feedback", arrayList[position].feedback)
                            startActivity(intent)
                        }

                    })

                    recyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}