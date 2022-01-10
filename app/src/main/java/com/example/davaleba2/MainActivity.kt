package com.example.davaleba2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var  mail : EditText
    private lateinit var  password : EditText
    private lateinit var  password1 : EditText
    private lateinit var  button : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        button.setOnClickListener {
            val mail = mail.text.toString()
            val password = password.text.toString()
            val password1 = password1.text.toString()

            if (mail.isEmpty() || password.isEmpty() || password1.isEmpty()) {
                Toast.makeText(this, "Try again!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (password != password1) {
                Toast.makeText(this, "Error, enter password again!", Toast.LENGTH_SHORT).show()
            }


            FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, password).addOnCompleteListener() { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Email already exists",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    private fun init(){
        mail = findViewById(R.id.mail)
        password = findViewById(R.id.password)
        password1 = findViewById(R.id.password1)
        button = findViewById(R.id.button)
    }
}

