package com.example.test1

import User_info
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.my_course.*

class My_Course : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var CourseList = ArrayList<String>()
        var uid: String = "" // 각 해당하는 회원의 유저아이디를 가져온다.
        val user = Firebase.auth.currentUser
        if (user != null) {
            uid = user.uid
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_course)
        val storage = Firebase.storage
        var db = Firebase.firestore
        var info = db.collection("user").document(uid)
        info.get().addOnSuccessListener { documentSnapshot ->
            var data = documentSnapshot.toObject<User_info>()
            CourseList = data?.DB_Course!!
        }
        course_list.adapter = Course_List_Adapter(this,CourseList)
    }
}
