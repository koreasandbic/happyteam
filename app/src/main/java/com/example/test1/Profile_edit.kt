package com.example.test1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.profile_edit.*


class Profile_edit : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_edit)
        var name : String = ""
        var age : String = ""
        var car : String = ""
        save.setOnClickListener {
            val nextIntent = Intent()
            nextIntent.putExtra("이름", name)
            nextIntent.putExtra("나이", age)
            nextIntent.putExtra("차종", car)
            setResult(Activity.RESULT_OK, nextIntent)
            finish()
        }
        Edit_Name.setOnClickListener {
            name = Edit_Name.getText().toString()
            Edit_Name.setText(name);
        }
        Edit_Age.setOnClickListener {
            age = Edit_Age.getText().toString()
            Edit_Age.setText(age);
        }
        Edit_Car.setOnClickListener {
            car = Edit_Car.getText().toString()
            Edit_Car.setText(car);
        }
    }
}