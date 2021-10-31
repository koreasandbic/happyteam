package com.example.test1

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.profile_edit.*
import java.io.ByteArrayOutputStream


class Profile_edit : AppCompatActivity() {
    private val GALLERY = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_edit)
        var name : String = ""
        var age : String = ""
        var car : String = ""
        save.setOnClickListener {
            val nextIntent = Intent()
            val stream = ByteArrayOutputStream()
            val bitmap = (Edit_Photo.getDrawable() as BitmapDrawable).bitmap
            val scale = (1024 / bitmap.width.toFloat())
            val image_w = (bitmap.width * scale).toInt()
            val image_h = (bitmap.height * scale).toInt()
            val resize = Bitmap.createScaledBitmap(bitmap, image_w, image_h, true)
            resize.compress(Bitmap.CompressFormat.JPEG, 100, stream)
            val byteArray: ByteArray = stream.toByteArray()
            nextIntent.putExtra("name", name)
            nextIntent.putExtra("age", age)
            nextIntent.putExtra("car", car)
            nextIntent.putExtra("image", byteArray)
            setResult(Activity.RESULT_OK, nextIntent)
            finish()
        }
        Edit_Photo.setOnClickListener{
            val intent : Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(intent,GALLERY)
        }
        Edit_Name.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                var temp : String = Edit_Name.text.toString()
                name = temp
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        Edit_Age.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                var temp : String = Edit_Age.text.toString()
                age = temp
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        Edit_Car.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                var temp : String = Edit_Car.text.toString()
                car = temp
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if( resultCode == Activity.RESULT_OK){
            if( requestCode == GALLERY) {
                var ImnageData: Uri? = data?.data
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, ImnageData)
                    Edit_Photo.setImageBitmap(bitmap)
                } catch (e:Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}