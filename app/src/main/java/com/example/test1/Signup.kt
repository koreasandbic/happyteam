package com.example.test1

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.search.*
import kotlinx.android.synthetic.main.signup_page.*

class Signup : AppCompatActivity() {
    private var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_page)
        auth = Firebase.auth
        sign_complete.setOnClickListener {
            createAccount(Make_id.text.toString(),Make_password.text.toString())
        }
    }

    private fun createAccount(email: String, password: String) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            this, "계정 생성 완료.",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish() // 가입창 종료
                    } else {
                        Toast.makeText(
                            this, "계정 생성 실패",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }
}