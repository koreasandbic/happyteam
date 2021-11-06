package com.example.test1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.hwasung.*

class Hwasung : AppCompatActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hwasung)

        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=431446%2C1173982%2C447499%2C1212529&rt1=%EB%AC%B8%EB%B0%9CIC&rt2=%EB%8B%B9%EB%8F%99IC&rtIds=10417432%2C10265299&rtTypes=PLACE%2CPLACE"))
        hwa_map.setOnClickListener {
            startActivity(mIntent)
        }

    }
}