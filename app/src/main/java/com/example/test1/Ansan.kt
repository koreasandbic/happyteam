package com.example.test1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ansan.*
import kotlinx.android.synthetic.main.hwasung.*

class Ansan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ansan)

        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=431487%2C1065538%2C406780%2C1053264&rt1=%EC%98%A4%EC%9D%B4%EB%8F%84%EA%B8%B0%EB%85%90%EA%B3%B5%EC%9B%90&rt2=%EB%8C%80%EB%B6%80%EB%8F%84%EA%B3%B5%EC%9B%90&rtIds=26957436%2C17384606&rtTypes=PLACE%2CPLACE"))
        ansan_map.setOnClickListener {
            startActivity(mIntent)
        }
    }
}
