package com.example.test1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_yongin.*
import kotlinx.android.synthetic.main.hwasung.*

class Yongin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yongin)

        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=543227%2C1054083%2C540215%2C1057920&rt1=%EA%B0%80%EC%8B%A4%EB%B2%9A%EA%BD%83%EA%B8%B8&rt2=%EB%B0%B1%EB%A0%A8%EC%82%AC&rtIds=19566713%2C26103109&rtTypes=PLACE%2CPLACE"))
        yong_map.setOnClickListener {
            startActivity(mIntent)
        }
    }
}
