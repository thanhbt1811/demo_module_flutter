package com.thanhbt1811.counterandroiod

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult

class MainActivity : AppCompatActivity() {
    private var count: Int = 10
    private lateinit var countText : TextView
    private val startForResult = registerForActivityResult(StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            handleData(intent)
        }
    }
    private fun handleData(data: Intent?){
        if (data != null) {
            count = data.getIntExtra("count_result",count)
            println("result count: $count")
            countText.text = "Count: $count"
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        countText = findViewById(R.id.count_text)
        countText.text = "Count: $count"
        button.setOnClickListener {
            val flutterIntent = Intent(this,CounterActivity::class.java)
            flutterIntent.putExtra("count",count)
            startForResult.launch(flutterIntent)
        }
        val goToHomeButton = findViewById<Button>(R.id.button_to_home)
        goToHomeButton.setOnClickListener {
            val flutterIntent = Intent(this,CounterActivity::class.java)
            flutterIntent.putExtra("route","/home")
            startForResult.launch(flutterIntent)
        }
    }

}