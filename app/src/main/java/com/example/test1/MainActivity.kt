package com.example.test1

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.frag_home.*
import kotlinx.android.synthetic.main.frag_like.*
import kotlinx.android.synthetic.main.frag_mypage.*
class MainActivity : AppCompatActivity() {

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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        viewList.add(layoutInflater.inflate(R.layout.frag_home, null))
        viewList.add(layoutInflater.inflate(R.layout.frag_like, null))
        viewList.add(layoutInflater.inflate(R.layout.frag_mypage, null))
        val intent_p = Intent(this, Profile_edit::class.java)
        val intent_c = Intent(this, My_Course::class.java)
        viewpager.adapter = pagerAdapter()
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
                    recycle.visibility = View.INVISIBLE
                    switch1.setOnClickListener {
                        if (switch1.isChecked){
                            check = 1
                            switch1.setTextColor(Color.WHITE)
                            Name.setTextColor(Color.WHITE)
                            textView_name.setTextColor(Color.WHITE)
                            Car.setTextColor(Color.WHITE)
                            textView_car.setTextColor(Color.WHITE)
                            course.setTextColor(Color.WHITE)
                            Age.setTextColor(Color.WHITE)
                            textView_age.setTextColor(Color.WHITE)
                            textView2.setTextColor(Color.WHITE)
                            profile.setTextColor(Color.WHITE)
                            frag_mypage.setBackgroundColor(Color.BLACK)
                            bottomNavigationView.setBackgroundColor(Color.BLACK)
                    }
                        else{
                            check = 0
                            switch1.setTextColor(Color.BLACK)
                            Name.setTextColor(Color.BLACK)
                            textView_name.setTextColor(Color.BLACK)
                            Car.setTextColor(Color.BLACK)
                            textView_car.setTextColor(Color.BLACK)
                            Age.setTextColor(Color.BLACK)
                            textView_age.setTextColor(Color.BLACK)
                            course.setTextColor(Color.BLACK)
                            textView2.setTextColor(Color.BLACK)
                            profile.setTextColor(Color.BLACK)
                            frag_mypage.setBackgroundColor(Color.WHITE)
                            bottomNavigationView.setBackgroundColor(Color.WHITE)
                    }
                    }
                    textView2.setOnClickListener {
                        var versionName = BuildConfig.VERSION_NAME
                        Toast.makeText(this, versionName, Toast.LENGTH_SHORT).show()
                    }
                    profile.setOnClickListener {
                        startActivityForResult(intent_p, 100)
                    }
                    course.setOnClickListener{
                        startActivity(intent_c)
                    }
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
                val byteArray = data?.getByteArrayExtra("image")
                val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray!!.size)
                Name.text = message1
                Age.text = message2
                Car.text = message3
                Photo.setImageBitmap(bitmap)
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


