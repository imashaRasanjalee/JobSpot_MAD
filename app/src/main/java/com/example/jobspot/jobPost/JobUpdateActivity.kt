package com.example.jobspot.jobPost

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jobspot.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class JobUpdateActivity : AppCompatActivity(){
    private lateinit var jobTitle: EditText
    private lateinit var jobEdu: EditText
    private lateinit var jobExperience: EditText
    private lateinit var jobAge: EditText
    private lateinit var jobType: EditText
    private lateinit var jobPayment: EditText
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var databaseReference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jobs_details)
        databaseReference = FirebaseDatabase.getInstance().getReference("jobs")
        initialView()
        toViews()

    }

    private fun initialView() {
        jobTitle = findViewById(R.id.editJobTitle)
        jobEdu = findViewById(R.id.editJobEdu)
        jobExperience = findViewById(R.id.editJobExp)
        jobAge = findViewById(R.id.editJobAge)
        jobType = findViewById(R.id.editJobType)
        jobPayment = findViewById(R.id.editJobPpay)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun toViews() {
        jobTitle.setText(intent.getStringExtra("title"))
        jobEdu.setText(intent.getStringExtra("education"))
        jobExperience.setText(intent.getStringExtra("experience"))
        jobAge.setText(intent.getStringExtra("age"))
        jobType.setText(intent.getStringExtra("type"))
        jobPayment.setText(intent.getStringExtra("payment"))

    }

    fun updatePost(view: View) {
        val title = jobTitle.text.toString()
        val education = jobEdu.text.toString()
        val experience = jobExperience.text.toString()
        val age = jobAge.text.toString()
        val type = jobType.text.toString()
        val payment = jobPayment.text.toString()
        val jobId = intent.getStringExtra("id").toString()
        if (title.isNotEmpty() && education.isNotEmpty() && experience.isNotEmpty() && age.isNotEmpty() && type.isNotEmpty() && payment.isNotEmpty() ){

            val model = JobModel(
                jobId = jobId,
                title = title,
                education = education,
                experience = experience,
                age = age,
                type = type,
                payment = payment,

                )

            databaseReference.child(jobId).setValue(model)
                .addOnCompleteListener {
                    Toast.makeText(this, "Job Post Updated", Toast.LENGTH_LONG).show()

                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        } else{
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }
    fun deletePost(view: View) {
        val id = intent.getStringExtra("id").toString()
        if (id.isNotEmpty()){
            val intent = Intent(this, FetchingActivity::class.java)
            databaseReference.child(id).setValue(null)
                .addOnCompleteListener {
                    Toast.makeText(this, "Job Post Removed", Toast.LENGTH_LONG).show()
                    startActivity(intent)
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

    }

}