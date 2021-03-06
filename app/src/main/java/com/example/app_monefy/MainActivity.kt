package com.example.app_monefy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("users")

        btnSave.setOnClickListener {
            savedata()
            val intent = Intent (this, Show::class.java)
            startActivity(intent)
        }
    }

    private fun savedata() {
        val nama = inputNama.text.toString()
        val status = inputStatus.text.toString()

        val userId = ref.push().key.toString()
        val user = Users(userId, nama, status)

        ref.child(userId).setValue(user).addOnCompleteListener {
            Toast.makeText(this, "Success",Toast.LENGTH_SHORT).show()
            inputNama.setText("")
            inputStatus.setText("")
        }
    }
}