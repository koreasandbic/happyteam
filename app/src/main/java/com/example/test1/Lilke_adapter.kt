package com.example.test1

import User_info
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.frag_mypage.*
import java.util.ArrayList

class Data_like(val profile_like: Int, val name_like:String)

class Like_adapter(val context: Context, var DataList:ArrayList<String>) : BaseAdapter()
{
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val storage = Firebase.storage
        val storageRef = storage.reference
        var db = Firebase.firestore
        val view:View = LayoutInflater.from(context).inflate(R.layout.custom_like, null)
        val profile_like = view.findViewById<ImageView>(R.id.like_iv)
        val name_like = view.findViewById<TextView>(R.id.like_tv)
        name_like.text = DataList[p0]
        var name = DataList[p0].plus(".jpg")
        var photo = "Background/".plus(name)
        var islandRef = storageRef.child(photo)
        val ONE_MEGABYTE: Long = 1024 * 1024
        islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
            var bitmap : Bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
            profile_like.setImageBitmap(bitmap)
        }
        return view
    }

    override fun getItem(p0: Int) =  DataList[p0]


    override fun getItemId(p0: Int) = 0L

    override fun getCount() = DataList.size

}
//이건 커밋용 주석