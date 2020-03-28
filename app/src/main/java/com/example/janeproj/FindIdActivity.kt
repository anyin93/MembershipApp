package com.example.janeproj

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_findid.*
import kotlinx.android.synthetic.main.activity_login.*

class FindIdActivity : AppCompatActivity()  {
    var EtEmailId:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(this.toString(),"onCreate()")

        setContentView(R.layout.activity_findid)

        //인스턴스를 얻어 변수처럼 사용한다.
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment,phonefragment())
            .commit()



        btn_findid_phone.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment,phonefragment())
                .commit()
        }

        btn_findid_email.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment,emailfragment())
                .commit()
        }
        btn_findid_auth.setOnClickListener{
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment,authfragment())
                .commit()
        }


        btn_findid_back.setOnClickListener{
            finish()
        }
        btn_findid_home.setOnClickListener{
            //새로 시작하려는 액티비티가 이전에 있을 경우 그 위에 모든 액티비티 다 지우고 새로 시작.
            intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d(this.toString(),"onStart()")
    }

    override fun onRestart() {
        super.onRestart()


        Log.d(this.toString(),"onReStart()")

    }


    override fun onResume() {
        Log.d(this.toString(),"onResume()")
        super.onResume()

        EtEmailId=null
    }

    override fun onPause() {
        Log.d(this.toString(),"onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.d(this.toString(),"onStop()")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(this.toString(),"onDestroy()")

        super.onDestroy()
    }




}