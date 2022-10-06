package cn.ymex.kitx.tps.umeng.mfr

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import cn.ymex.kitx.tps.umeng.PushMessage
import cn.ymex.kitx.tps.umeng.UmengManager
import com.umeng.message.UmengNotificationClickHandler
import com.umeng.message.UmengNotifyClick
import com.umeng.message.entity.UMessage

open class PushHoldActivity : Activity() {

    private val mNotificationClick = object : UmengNotifyClick(){
         override fun onMessage(message: UMessage) {
            try {
                val deal = UmengManager.instance.messageHandler?.dealWithNativeMessage(this@PushHoldActivity,
                    PushMessage(message)
                )?:true
                runOnUiThread {
                    if (deal){
                        //message.clickOrDismiss = true
                        message.dismiss = true
                        UmengNotificationClickHandler().handleMessage(this@PushHoldActivity,message)
                    }
                    finish()
                }
            }catch (e:Exception){
                e.printStackTrace()
            }

        }
    }

    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
        mNotificationClick.onCreate(this, intent)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        mNotificationClick.onNewIntent(intent)
    }


}