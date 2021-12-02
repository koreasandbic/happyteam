package com.example.test1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import java.util.ArrayList

class Data_like(val profile_like: Int, val name_like:String)

class Like_adapter(val context: Context, val DataList:ArrayList<Data_like>) : BaseAdapter()
{
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view:View = LayoutInflater.from(context).inflate(R.layout.custom_like, null)
        val profile_like = view.findViewById<ImageView>(R.id.like_iv)
        val name_like = view.findViewById<TextView>(R.id.like_tv)
        val data_like = DataList[p0]

        profile_like.setImageResource(data_like.profile_like)
        name_like.text = data_like.name_like

        return view
    }

    override fun getItem(p0: Int) =  DataList[p0]


    override fun getItemId(p0: Int) = 0L

    override fun getCount() = DataList.size

}
//이건 커밋용 주석이얌