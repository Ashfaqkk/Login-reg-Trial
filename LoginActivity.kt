package com.example.loginfiretrial


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity() {
    private var mAuth: FirebaseAuth? = null
    private var isProcessing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initialize()
        setClick()
        textView.setOnClickListener{
            Toast.makeText(applicationContext,"sucess",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@LoginActivity, RegistrationActivity::class.java))
            finish()
        }
    }

    fun initialize() {
        mAuth = FirebaseAuth.getInstance()
    }

    fun setClick() {
        btnLogin.setOnClickListener {
                if (etEmail.text.toString().trim().isNotEmpty() && etPassword.text.toString().trim().isNotEmpty()) {

                    performLogin()


                } else {
                    showMessage(this, getString(R.string.fill_all))
                }

        }
    }

    fun performLogin() {
        mAuth?.signInWithEmailAndPassword(
            etEmail.text.toString().trim(),
            etPassword.text.toString().trim()
        )
            ?.addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    /*mAuth!!.currentUser!!.email.toString()
                    Toast.makeText(applicationContext,"Sucess",Toast.LENGTH_SHORT).show()*/
                    intent = Intent(applicationContext, MainActivity::class.java)
                    startActivity(intent)
                    } else {
                    showMessage(this, getString(R.string.no_network))
                }
            }
    }
}
