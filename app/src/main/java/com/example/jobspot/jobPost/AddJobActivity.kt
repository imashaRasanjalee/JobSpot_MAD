package com.example.jobspot.jobPost

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jobspot.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddJobActivity : AppCompatActivity() {

    private lateinit var jobTitle: EditText
    private lateinit var jobEdu: EditText
    private lateinit var jobExperience: EditText
    private lateinit var jobAge: EditText
    private lateinit var jobType: EditText
    private lateinit var jobPayment: EditText

    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_job)

        jobTitle = findViewById(R.id.jobTitle)
        jobEdu = findViewById(R.id.jobEducation)
        jobExperience = findViewById(R.id.jobExperience)
        jobAge = findViewById(R.id.jobAge)
        jobType = findViewById(R.id.jobRequired)
        jobPayment = findViewById(R.id.jobPayment)
        reference = FirebaseDatabase.getInstance().getReference("jobs")
    }

    fun goFeedBack(view: View) {
        val intent = Intent(this, FeedBackMain::class.java)
        startActivity(intent)
    }

    fun postJobs(view: View) {
        val title = jobTitle.text.toString()
        val education = jobEdu.text.toString()
        val experience = jobExperience.text.toString()
        val age = jobAge.text.toString()
        val type = jobType.text.toString()
        val payment = jobPayment.text.toString()

        if (title.isNotEmpty() && education.isNotEmpty() && experience.isNotEmpty() && age.isNotEmpty() && type.isNotEmpty() && payment.isNotEmpty()){

            val docid = reference.push().key!!

            val model = JobModel(
                jobId = docid,
                title = title,
                education = education,
                experience = experience,
                age = age,
                type = type,
                payment = payment,

            )

            reference.child(docid).setValue(model)
                .addOnCompleteListener {
                    Toast.makeText(this, "Job Post successful", Toast.LENGTH_LONG).show()
                    jobTitle.setText(null)
                    jobEdu.setText(null)
                    jobExperience.setText(null)
                    jobType.setText(null)
                    jobAge.setText(null)
                    jobPayment.setText(null)

                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
            //startActivity(intent)
        } else{
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }
    fun goBack(view: View) {}


}