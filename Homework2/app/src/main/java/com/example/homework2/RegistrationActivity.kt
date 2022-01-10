package com.example.homework2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {
    private lateinit var  email : EditText
    private lateinit var  password : EditText
    private lateinit var  password2 : EditText
    private lateinit var  btn : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registrationxml)


        init()
        btn.setOnClickListener {
            val mail = email.text.toString()
            val password = password.text.toString()
            val password2 = password2.text.toString()

            if (mail.isEmpty() || password.isEmpty() || password2.isEmpty()) {
                Toast.makeText(this, "Oops, Try again!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (password != password2) {
                Toast.makeText(this, "Enter a password again!", Toast.LENGTH_SHORT).show()
            }


            FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail, password).addOnCompleteListener() { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, RegistrationActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(
                            this,
                            "Email is already used",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
    private fun init(){
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        password2 = findViewById(R.id.password2)
        btn = findViewById(R.id.btn)
    }
}