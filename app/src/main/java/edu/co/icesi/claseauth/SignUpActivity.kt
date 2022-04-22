package edu.co.icesi.claseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.co.icesi.claseauth.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.signupBtn.setOnClickListener {
            val email = binding.signupEmailET.text.toString()
            val password = binding.signupPassET.text.toString()

            Firebase.auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                Toast.makeText(this, "todo bien", Toast.LENGTH_SHORT).show()
                registerUserData()
            }.addOnFailureListener {
                Toast.makeText(this, "fallo ${it.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun registerUserData() {
        val uid = Firebase.auth.currentUser?.uid
        val user = User(
            binding.signupUsernameET.text.toString(),
            binding.signupDescET.text.toString(),
            binding.signupEmailET.text.toString()
        )
        uid?.let {
            Firebase.firestore.collection("users").document(it).set(user).addOnSuccessListener {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
        }
    }


}