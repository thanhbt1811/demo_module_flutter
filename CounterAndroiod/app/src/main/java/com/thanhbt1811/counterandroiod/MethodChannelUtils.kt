package com.thanhbt1811.counterandroiod

import android.content.Context
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel

typealias handlerMethodCall = (MethodCall, MethodChannel.Result) -> Unit

class MethodChannelUtils(channelName: String) {
    private var channel: MethodChannel

    companion object {
        private lateinit var context: Context
        fun setContext(context: Context) {
            this.context = context
        }
    }

    init {
        val app = context.applicationContext as App
        channel = MethodChannel(app.flutterEngine.dartExecutor.binaryMessenger, channelName)
    }

    fun invokeMethod(methodName: String, arg: Any) {
        channel.invokeMethod(methodName, arg)
    }

    fun handlerMethod(handler: handlerMethodCall) {
        channel.setMethodCallHandler { call, result -> handler.invoke(call, result) }
    }
}