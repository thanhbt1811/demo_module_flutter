package com.thanhbt1811.counterandroiod

import android.content.Context
import android.content.Intent
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodChannel

class CounterActivity : FlutterActivity() {
    var count = 0
    lateinit var channel: MethodChannel
    override fun onCreate(savedInstanceState: Bundle?,) {
        super.onCreate(savedInstanceState)
        val app = context.applicationContext as App
        val extras = intent.extras
        if (extras != null){
            count = extras.getInt("count")
        }
        channel = MethodChannel(app.flutterEngine.dartExecutor,"counter_method")
        channel.invokeMethod("setCounter",count)
        channel.setMethodCallHandler{ methodCall, result ->
            if (methodCall.method == "next"){
                val intent = Intent(this,SecondAvtivity::class.java)
                intent.putExtra("count",count)
                startActivityForResult(intent,1)
                result.success(null)
            }
            if (methodCall.method == "back"){
                val intent = Intent()
                intent.putExtra("count_result",count)
                setResult(RESULT_OK,intent)
                finish()
                result.success(null)
            }
            if (methodCall.method == "incrementCounter"){
                count = methodCall.argument<Int>("counter")?.toInt() ?: app.count
                result.success(null)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK){
            count = data?.extras?.getInt("count_result") ?: count
            channel.invokeMethod("setCounter",count)
        }
    }

    override fun provideFlutterEngine(context: Context): FlutterEngine? {
        return FlutterEngineCache.getInstance().get("flutter_engine")
    }

    override fun getInitialRoute(): String {
        val extras = intent.extras
        var route = "/"
        if (extras != null){
            route = extras.getString("route") ?: route
        }
        return route
    }}