package edu.co.icesi.claseauth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.co.icesi.claseauth.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private val binding: ActivityProfileBinding by lazy{
        ActivityProfileBinding.inflate(layoutInflater);
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(Firebase.auth.currentUser == null){
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

        binding.profileSignoutBtn.setOnClickListener {
            Firebase.auth.signOut()
        }



        binding.profileWelcomeTV.text = Firebase.auth.currentUser?.uid.toString()
    }
}