package com.example.test1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_ansung.*
import kotlinx.android.synthetic.main.hwasung.*

class Ansung : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ansung)

        val mIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://map.kakao.com/?map_type=TYPE_MAP&target=car&rt=562241%2C974843%2C581472%2C966001&rt1=%EC%95%88%EC%84%B1%EC%8B%9C%EC%B2%AD&rt2=%EC%98%A5%EC%9E%A5%EB%8F%99%EB%A7%88%EC%9D%84%ED%9A%8C%EA%B4%80&rtIds=7992450%2C27431890&rtTypes=PLACE%2CPLACE"))
        ansung_map.setOnClickListener {
            startActivity(mIntent)
        }
    }
}
