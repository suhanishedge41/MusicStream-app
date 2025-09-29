package com.example.musicstreamapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.musicstreamapp.databinding.ActivitySingupBinding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class SingupActivity : AppCompatActivity() {

    lateinit var binding : ActivitySingupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createAccountBtn.setOnClickListener{
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()

           if(!Pattern.matches(Patterns.EMAIL_ADDRESS.pattern(),email)){
               binding.emailEditText.setError("Invalid email")
               return@setOnClickListener
           }

            if(password.length < 6){
                binding.passwordEditText.setError("Length should be 6 char")
                return@setOnClickListener
            }

            if(!password.equals(confirmPassword)){
                binding.confirmPasswordEditText.setError("Password not matches")
                return@setOnClickListener
            }

            createAccountWithFirebase(email,password)
        }
        binding.gotoLoginBtn.setOnClickListener {
            finish()
        }

    }
    fun createAccountWithFirebase(email : String,password : String){
          setInProgress(true)
          FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
              .addOnSuccessListener {
                  setInProgress(false)
                  Toast.makeText(applicationContext,"User created succesfully",Toast.LENGTH_SHORT).show()
                  finish()

              }.addOnFailureListener {
                  setInProgress(false)
                  Toast.makeText(applicationContext,"Create account failed",Toast.LENGTH_SHORT).show()
              }
    }
    fun setInProgress(inProgress : Boolean){
        if(inProgress){
            binding.createAccountBtn.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.createAccountBtn.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
        }
    }

}