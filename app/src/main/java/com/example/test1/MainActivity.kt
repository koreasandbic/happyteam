package com.example.test1

import User_info
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.firebase.ui.storage.BuildConfig
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frag_home.*
import kotlinx.android.synthetic.main.frag_like.*
import kotlinx.android.synthetic.main.frag_mypage.*


class MainActivity : AppCompatActivity() {
    private var auth : FirebaseAuth? = null
    val DataList = arrayListOf(
        Data(profile = R.mipmap.hwa_1, name = "화성방조제길"),
        Data(R.mipmap.paju_1, name = "자유로"),
        Data(R.mipmap.pochen, name = "국립수목원로"),
        Data(R.mipmap.gapeong, name = "청평호반길,북한강변길"),
        Data(R.mipmap.gwang, name = "남한산성,팔당호 벚꽃길"),
        Data(R.mipmap.nam, name = "화음길"),
        Data(R.mipmap.yang, name = "두물머리 강변길"),
        Data(R.mipmap.yong, name = "가실벚꽃길"),
        Data(R.mipmap.ansung, name = "금광호수로"),
        Data(R.mipmap.ansan, name = "시화방조제길")
    )
    var viewList = ArrayList<View> ()
    var check = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        var uid : String = "" // 각 해당하는 회원의 유저아이디를 가져온다.
        val user = Firebase.auth.currentUser
        if (user != null) {
            uid = user.uid
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        viewList.add(layoutInflater.inflate(R.layout.frag_home, null))
        viewList.add(layoutInflater.inflate(R.layout.frag_like, null))
        viewList.add(layoutInflater.inflate(R.layout.frag_mypage, null))
        val intent_p = Intent(this, Profile_edit::class.java)
        val intent_c = Intent(this, My_Course::class.java)
        viewpager.adapter = pagerAdapter()
        auth = Firebase.auth
        var CourseList = ArrayList<String>()
        viewpager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> bottomNavigationView.selectedItemId = R.id.home
                    1 -> bottomNavigationView.selectedItemId = R.id.like
                    2 -> bottomNavigationView.selectedItemId = R.id.profile
                }
            }
        })
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home -> {viewpager.setCurrentItem(0)
                    recycle.visibility = View.VISIBLE
                    if(check == 1){
                        frag_home.setBackgroundColor(Color.BLACK)
                    }
                    else{
                        frag_home.setBackgroundColor(Color.WHITE)
                    }
                }
                R.id.like -> {viewpager.setCurrentItem(1)
                    recycle.visibility = View.INVISIBLE
                    if(check == 1){
                        frag_like.setBackgroundColor(Color.BLACK)
                    }
                    else{
                        frag_like.setBackgroundColor(Color.WHITE)
                    }
                }
                R.id.profile -> {viewpager.setCurrentItem(2)
                    val storage = Firebase.storage
                    val storageRef = storage.reference
                    var db = Firebase.firestore
                    recycle.visibility = View.INVISIBLE
                    var info = db.collection("user").document(uid)
                        info.get().addOnSuccessListener { documentSnapshot ->
                            var data = documentSnapshot.toObject<User_info>()
                            Name.setText(data?.DB_Name)
                            Age.setText(data?.DB_Age)
                            Car.setText(data?.DB_Car)
                            CourseList = data!!.DB_Course
                            var islandRef = storageRef.child(uid)
                            val ONE_MEGABYTE: Long = 1024 * 1024
                            islandRef.getBytes(ONE_MEGABYTE).addOnSuccessListener {
                                var bitmap : Bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                                Photo.setImageBitmap(bitmap)
                            }
                        }
                    //데이터 베이스에 저장되어 있는 데이터들을 user_info데이터 클래스로 불러온다. 그후 이름,나이,차종을 저장한다.
                    App_version.setOnClickListener {
                        val versionName = BuildConfig.VERSION_NAME
                        Toast.makeText(this, versionName, Toast.LENGTH_SHORT).show()
                    }
                    profile.setOnClickListener {
                        startActivityForResult(intent_p, 100)
                    }
                    course.setOnClickListener{
                        intent_c.putExtra("array",CourseList)
                        startActivity(intent_c)
                    }
                    Logout.setOnClickListener{
                        val intent = Intent(this, Login::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)//화면 전환
                        auth?.signOut()
                    }//인텐트를 사용하여 로그인 화면으로 이동하면서 로그아웃을 한다.
                }
            }
            true
        }
        recycle.layoutManager = LinearLayoutManager(this)
        recycle.adapter = CustomAdapter(DataList, this)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode){
            Activity.RESULT_OK -> {
                val message1 = data?.getStringExtra("name")
                val message2 = data?.getStringExtra("age")
                val message3 = data?.getStringExtra("car")
                Name.text = message1
                Age.text = message2
                Car.text = message3
            }
        }
    }

    inner class pagerAdapter : PagerAdapter() {
        override fun isViewFromObject(view: View, `object`: Any) = view == `object`

        override fun getCount() = viewList.size
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            var curView = viewList[position]
            viewpager.addView(curView)
            return curView
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            viewpager.removeView(`object` as View)
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.tool_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var intent = Intent(this, Search::class.java)
            when(item.itemId) {
                R.id.search -> startActivity(intent)
            }
            return super.onOptionsItemSelected(item)
    }
}

