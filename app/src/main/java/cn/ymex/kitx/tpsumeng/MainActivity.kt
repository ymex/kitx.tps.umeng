package cn.ymex.kitx.tpsumeng

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cn.ymex.kitx.tps.umeng.UmengManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        if (!UmengManager.hasAgreePrivacyAgreement()){
            showAgreementDialog()

        }

    }

    private fun showAgreementDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("隐私政策授权")
        builder.setMessage("隐私政策说明…")
        builder.setPositiveButton("同意",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
                //用户点击隐私协议同意按钮后，初始化PushSDK
                UmengManager.setAgreePrivacyAgreement(true)
                UmengManager.initPush()
            })
        builder.setNegativeButton("拒绝",
            DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
                finish()
            })
        builder.create().show()
    }
}