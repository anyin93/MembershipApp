package com.example.janeproj

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_join.*
import kotlinx.android.synthetic.main.activity_login.*

class JoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        btn_join_back.setOnClickListener{
            finish()
        }
        btn_join_home.setOnClickListener{
            //새로 시작하려는 액티비티가 이전에 있을 경우 그 위에 모든 액티비티 다 지우고 새로 시작.
            intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}
