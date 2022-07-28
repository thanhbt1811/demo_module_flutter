package com.thanhbt1811.counterandroiod

import android.app.Application
import io.flutter.FlutterInjector
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

class App : Application() {

    lateinit var flutterEngine: FlutterEngine
    var count: Int = 10
    override fun onCreate() {
        super.onCreate()
        flutterEngine = FlutterEngine(this)

        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint(
                FlutterInjector.instance().flutterLoader().findAppBundlePath(), "main"
            ),
        )
        FlutterEngineCache
            .getInstance()
            .put("flutter_engine", flutterEngine)
        MethodChannelUtils.Companion.setContext(this)

    }
}
