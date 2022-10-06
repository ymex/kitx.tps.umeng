package cn.ymex.kitx.tpsumeng

import android.app.Application
import android.content.Context
import cn.ymex.kitx.tps.umeng.PushMessage
import cn.ymex.kitx.tps.umeng.UmengConfig
import cn.ymex.kitx.tps.umeng.UmengManager

class AppContext : Application() {


    override fun onCreate() {
        super.onCreate()
        initUmeng()
    }

    fun initUmeng() {
        val uConfig = UmengConfig(
            "633d36cd52d9b44b1aae581d",
            "4c524615d9392cd8358e280e38056cc0",
            "office",
            true
        )
        UmengManager.init(this)
        UmengManager.instance.run {
            registerCallback = { success, token ->
                println("--------push:$success    token:$token")
            }

            messageHandler = object : UmengManager.MessageHandler {
                override fun dealWithMessage(context: Context, type: String, message: PushMessage) {
                    println("--------:dealWithMessage type:$type    msg:${message.body.custom}")
                }

                override fun dealWithNativeMessage(
                    context: Context,
                    message: PushMessage
                ): Boolean {
                    println("--------:dealWithNativeMessage    msg:${message.body.content}")
                    return true
                }

                override fun notificationClickCustomAction(context: Context, message: PushMessage) {

                    println("--------:notificationClickCustomAction    msg:${message.body.content}")
                }

            }
        }
        UmengManager.initPush(uConfig)

    }
}