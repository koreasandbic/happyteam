package com.example.test1

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.frag_home.*

class MainActivity : AppCompatActivity() {

    val DataList = arrayListOf(
        Data(R.drawable.ic_launcher_background, name = "0번"),
        Data(R.drawable.ic_launcher_background, name = "1번"),
        Data(R.drawable.ic_launcher_background, name = "2번"),
        Data(R.drawable.ic_launcher_background, name = "3번"),
        Data(R.drawable.ic_launcher_background, name = "4번"),
        Data(R.drawable.ic_launcher_background, name = "5번"),
        Data(R.drawable.ic_launcher_background, name = "6번"),
        Data(R.drawable.ic_launcher_background, name = "7번"),
        Data(R.drawable.ic_launcher_background, name = "8번"),
        Data(R.drawable.ic_launcher_background, name = "9번"),
        Data(R.drawable.ic_launcher_background, name = "10번"),
        Data(R.drawable.ic_launcher_background, name = "11번"),
        Data(R.drawable.ic_launcher_background, name = "12번")
    )

    var viewList = ArrayList<View> ()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        viewList.add(layoutInflater.inflate(R.layout.frag_home, null))
        viewList.add(layoutInflater.inflate(R.layout.frag_like, null))
        viewList.add(layoutInflater.inflate(R.layout.frag_mypage, null))

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
                    recycle.visibility = View.VISIBLE}
                R.id.like -> {viewpager.setCurrentItem(1)
                    recycle.visibility = View.INVISIBLE}
                R.id.profile -> {viewpager.setCurrentItem(2)
                    recycle.visibility = View.INVISIBLE}
            }
            true

        }

        recycle.layoutManager = LinearLayoutManager(this)
        recycle.adapter = CustomAdapter(DataList, this)


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


