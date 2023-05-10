package com.example.jobspot.feedBack

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.jobspot.R
import com.google.firebase.database.*


class UpdateFeedActivity : AppCompatActivity(){
    private lateinit var fTitle: EditText
    private lateinit var fUsername: EditText
    private lateinit var fEmail: EditText
    private lateinit var fPhone: EditText
    private lateinit var fDescription: EditText
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button
    private lateinit var dbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feedback_details)
        dbRef = FirebaseDatabase.getInstance().getReference("feedBacks")
        initView()
        setValuesToViews()

    }

    private fun initView() {
        fTitle = findViewById(R.id.editFeed)
        fUsername = findViewById(R.id.editName)
        fEmail = findViewById(R.id.editEmail)
        fPhone = findViewById(R.id.editPhone)
        fDescription = findViewById(R.id.editFeedBack)

        btnUpdate = findViewById(R.id.btnUpdate)
        btnDelete = findViewById(R.id.btnDelete)
    }

    private fun setValuesToViews() {
        fTitle.setText(intent.getStringExtra("title"))
        fUsername.setText(intent.getStringExtra("name"))
        fEmail.setText(intent.getStringExtra("email"))
        fPhone.setText(intent.getStringExtra("mobile"))
        fDescription.setText(intent.getStringExtra("feedback"))

    }

    fun editFeedback(view: View) {
        val title = fTitle.text.toString()
        val name = fUsername.text.toString()
        val email = fEmail.text.toString()
        val phone = fPhone.text.toString()
        val description = fDescription.text.toString()
        val feedback = intent.getStringExtra("id").toString()
        if (title.isNotEmpty() && description.isNotEmpty() && name.isNotEmpty() && phone.isNotEmpty()  && email.isNotEmpty()){
            
            val model = FeedBackModel(
                id = feedback,
                title = title,
                name = name,
                email = email,
                phone = phone,
                feedback = description,

                )

            dbRef.child(feedback).setValue(model)
                .addOnCompleteListener {
                    Toast.makeText(this, "FeedBack Updated", Toast.LENGTH_LONG).show()

                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }

        } else{
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }
    fun deleteFeedback(view: View) {
        val feedback = intent.getStringExtra("id").toString()
        if (feedback.isNotEmpty()){
            val intent = Intent(this, FetchingActivity::class.java)
            dbRef.child(feedback).setValue(null)
                .addOnCompleteListener {
                    Toast.makeText(this, "FeedBack Deleted", Toast.LENGTH_LONG).show()
                    fTitle.setText(null)
                    fUsername.setText(null)
                    fEmail.setText(null)
                    fPhone.setText(null)
                    fDescription.setText(null)
                    startActivity(intent)
                }.addOnFailureListener { err ->
                    Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
                }
        }

    }

}