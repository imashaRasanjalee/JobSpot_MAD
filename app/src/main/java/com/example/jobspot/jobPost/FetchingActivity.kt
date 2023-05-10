package com.example.jobspot.jobPost

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
    private lateinit var arrayList: ArrayList<JobModel>
    private lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fetching)

        recyclerView = findViewById(R.id.rvCom)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.tvLoadingData)

        arrayList = arrayListOf<JobModel>()

        getData()

    }

    private fun getData() {

        recyclerView.visibility = View.GONE
        tvLoadingData.visibility = View.VISIBLE

        dbRef = FirebaseDatabase.getInstance().getReference("jobs")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                arrayList.clear()
                if (snapshot.exists()){
                    for (empSnap in snapshot.children){
                        val dataJobs = empSnap.getValue(JobModel::class.java)
                        arrayList.add(dataJobs!!)
                    }
                    val mAdapter = JobAdapter(arrayList)
                    recyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object : JobAdapter.onItemClickListener {
                        override fun onItemClick(position: Int) {

                            val intent = Intent(this@FetchingActivity, JobUpdateActivity::class.java)
                            intent.putExtra("id", arrayList[position].jobId)
                            intent.putExtra("title", arrayList[position].title)
                            intent.putExtra("education", arrayList[position].education)
                            intent.putExtra("experience", arrayList[position].experience)
                            intent.putExtra("age", arrayList[position].age)
                            intent.putExtra("type", arrayList[position].type)
                            intent.putExtra("payment", arrayList[position].payment)
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