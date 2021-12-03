package com.example.test1

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.util.ArrayList


class Like_adapter(val context: Context, var DataList:ArrayList<String>) : BaseAdapter()
{
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var uid : String = "" // 각 해당하는 회원의 유저아이디를 가져온다.
        val user = Firebase.auth.currentUser
        if (user != null) {
            uid = user.uid
        }
        var db = Firebase.firestore
        var info = db.collection("user").document(uid)
        val storage = Firebase.storage
        val storageRef = storage.reference
        val view:View = LayoutInflater.from(context).inflate(R.layout.custom_like, null)
        val profile_like = view.findViewById<ImageView>(R.id.like_iv)
        val name_like = view.findViewById<TextView>(R.id.like_tv)
        val delete = view.findViewById<Button>(R.id.local_OFF)
        name_like.text = DataList[p0]
        var name = DataList[p0].plus(".jpg")
        var photo = "Background/".plus(name)
        var islandRef = storageRef.child(photo)
        val ONE_MEGABYTE: Long = 1024 * 1024
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            var bitmap : Bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            profile_like.setImageBitmap(bitmap)
        }
        delete.setOnClickListener {
            val updates = hashMapOf<String, Any>(
                "db_Favorite" to FieldValue.arrayRemove(name_like.text)
            )
            info.update(updates)
        }
        return view
    }

    override fun getItem(p0: Int) =  DataList[p0]


    override fun getItemId(p0: Int) = 0L

    override fun getCount() = DataList.size
}
//이건 커밋용 주석