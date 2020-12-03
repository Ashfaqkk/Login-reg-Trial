package com.example.loginfiretrial

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class RegistrationActivity : BaseActivity() {
    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var btnSignIn: Button? = null
    private var btnSignUp: Button? = null
    private var btnResetPassword: Button? = null

    private var auth : FirebaseAuth?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        auth = FirebaseAuth.getInstance()

        btnSignUp = findViewById(R.id.btnSignup) as Button
        inputEmail = findViewById(R.id.reEmail) as EditText
        inputPassword = findViewById(R.id.rePassword) as EditText


        btnSignUp!!.setOnClickListener(View.OnClickListener {
            val email = inputEmail!!.text.toString().trim()
            val password = inputPassword!!.text.toString().trim()

            if (TextUtils.isEmpty(email)){
                showMessage(this, getString(R.string.fill_all))
            }
            if (TextUtils.isEmpty(password)){
                showMessage(this, getString(R.string.fill_all))
            }
            if (password.length < 6){
                showMessage(this, getString(R.string.fill_all))
                return@OnClickListener
            }

            //create user
            auth!!.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, OnCompleteListener {
                        task ->

                    if (!task.isSuccessful){
                        showMessage(this, getString(R.string.not_created))
                        return@OnCompleteListener
                    }else{
                        startActivity(Intent(this@RegistrationActivity, MainActivity::class.java))
                        finish()
                    }


                })

        })
    }
}