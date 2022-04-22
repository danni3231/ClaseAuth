package edu.co.icesi.claseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.co.icesi.claseauth.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginNoAccountTV.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.loginSignInBtn.setOnClickListener {
            val email = binding.loginEmailET.text.toString()
            val password = binding.loginPassET.text.toString()

            Firebase.auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                startActivity(Intent(this,ProfileActivity::class.java))
            }
        }


    }
}