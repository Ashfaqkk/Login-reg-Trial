package com.example.loginfiretrial

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.constraintlayout.widget.ConstraintLayout


abstract class BaseActivity : AppCompatActivity() {

    lateinit var mProgressDialog: ConstraintLayout
    lateinit var toolbar: Toolbar
    private lateinit var progressdialog: ProgressDialog
    private lateinit var messageDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    fun showMessage(context: Context, message: String) {

        if (::messageDialog.isInitialized) {
            if (messageDialog != null && messageDialog.isShowing) {
                messageDialog.dismiss()
            }
        }
        messageDialog = Dialog(
            context,
            0
        )

        messageDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        messageDialog.setContentView(R.layout.layout_message)

        //messageDialog.window!!.attributes.windowAnimations = R.style.PopDialogAnimation
        messageDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val lp = messageDialog.window!!.attributes
        lp.gravity = Gravity.BOTTOM
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.MATCH_PARENT
        lp.dimAmount = 0.7f
        messageDialog.window!!.attributes = lp

        messageDialog.window!!.attributes.dimAmount = 0.4f
        messageDialog.findViewById<TextView>(R.id.tv_message).text = message
        messageDialog.findViewById<TextView>(R.id.tv_cancel).setOnClickListener {

            messageDialog.dismiss()


        }
        messageDialog.setCancelable(true)
        messageDialog.show()

    }
}