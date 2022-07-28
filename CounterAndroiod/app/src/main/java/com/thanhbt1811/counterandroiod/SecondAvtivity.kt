package com.thanhbt1811.counterandroiod

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import io.flutter.plugin.common.MethodChannel

class SecondAvtivity : AppCompatActivity() {
    var count: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_avtivity)
        val button = findViewById<Button>(R.id.button)
        val countText = findViewById<TextView>(R.id.count_text)
        val extras = intent.extras
        if (extras != null){
            count = extras.getInt("count")
            countText.text = "Count: $count"
        }
        button.setOnClickListener {
            val intent = Intent()
            intent.putExtra("count_result",count+10)
            setResult(RESULT_OK,intent)
            finish()
        }
    }
}