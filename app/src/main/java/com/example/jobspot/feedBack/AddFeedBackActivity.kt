package com.example.jobspot.feedBack

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jobspot.FeedBackMain
import com.example.jobspot.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class AddFeedBackActivity : AppCompatActivity() {

    private lateinit var feedTitle: EditText
    private lateinit var feedName: EditText
    private lateinit var feedEmail: EditText
    private lateinit var feedPhone: EditText
    private lateinit var feedback: EditText

    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_feedback)

        feedTitle = findViewById(R.id.feedTitle)
        feedName = findViewById(R.id.feedUName)
        feedEmail = findViewById(R.id.feedEmail)
        feedPhone = findViewById(R.id.feedMobile)
        feedback = findViewById(R.id.feedBack)
        reference = FirebaseDatabase.getInstance().getReference("feedBacks")
    }

    fun goFeedBack(view: View) {
        val intent = Intent(this, FeedBackMain::class.java)
        startActivity(intent)
    }

    fun addFeedback(view: View) {
        val title = feedTitle.text.toString()
        val name = feedName.text.toString()
        val email = feedEmail.text.toString()
        val phone = feedPhone.text.toString()
        val feedback1 = feedback.text.toString()

        if (title.isNotEmpty() && name.isNotEmpty() && email.isNotEmpty() && phone.isNotEmpty() && feedback1.isNotEmpty() ){

            val docid = reference.push().key!!

            val model = FeedBackModel(
                id = docid,
                title = title,
                name = name,
                email = email,
                phone = phone,
                feedback = feedback1,

                )

            reference.child(docid).setValue(model)
                .addOnCompleteListener {
                    Toast.makeText(this, "FeedBack Added successful", Toast.LENGTH_LONG).show()
                    feedTitle.setText(null)
                    feedName.setText(null)
                    feedEmail.setText(null)
                    feedPhone.setText(null)
                    feedback.setText(null)

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