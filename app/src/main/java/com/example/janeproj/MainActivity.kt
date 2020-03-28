package com.example.janeproj

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var pref:SharedPreferences?=null
    var editor:SharedPreferences.Editor?= null

    var prefcupon:SharedPreferences?=null
    var editorcupon:SharedPreferences.Editor?=null

    var colorArr = emptyArray<String>()
    var advArr= emptyArray<String>()
    var colorIndex:Int=0
    var advIndex:Int=0

    var EtLoginPw:String?=null
    var EtLoginId:String?=null
    var loginFlag:Boolean?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(this.toString(),"onCreate()")

        setContentView(R.layout.activity_main)

        pref=getSharedPreferences("session",0)
        editor=pref?.edit()
        prefcupon=getSharedPreferences("cupon",0)
        editorcupon=pref?.edit()

        loginFlag=false

        colorArr=arrayOf("#800000","#8B4513","#006400","#46B4B4","#505050")
        advArr=arrayOf("@drawable/main_adver","@drawable/main_adver2","@drawable/main_adver3")
        colorIndex=0
        advIndex=0

        btn_main_login.setOnClickListener{



            if(loginFlag==true) {
                Toast.makeText(this,"쿠폰 확인",Toast.LENGTH_SHORT).show()
                val intent=Intent(this,CuponActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this,"로그인 및 회원가입",Toast.LENGTH_SHORT).show()
                val intent=Intent("janeFirst.member")
                startActivity(intent);

            }




        }



    }

    override fun onStart() {
        super.onStart()
        Log.d(this.toString(),"onStart()")

        EtLoginId=pref?.getString("id",null)
        EtLoginPw=pref?.getString("pw",null)
        Log.d(this.toString(),EtLoginId+"     "+EtLoginPw)

        if(EtLoginId!=null && EtLoginPw!=null){loginFlag=true}

        if(loginFlag==true){

                tv_main_msg1.setText("my 쿠폰")
                tv_main_msg2.text="보유쿠폰 : 1개"
                tv_main_msg3.text="마이 쿠폰을 확인해보세요."
                btn_main_login.text="쿠폰 확인"
                loginFlag=true;


        }

    }

    override fun onRestart() {
        super.onRestart()
        Log.d(this.toString(),"onReStart()")

        colorIndex++
        main_window.setBackgroundColor(Color.parseColor(colorArr[colorIndex]))
        if(colorIndex==colorArr.size-1)colorIndex=-1

        advIndex++
        val resName=advArr[advIndex].toString()
        val resID=resources.getIdentifier(resName,"drawable",packageName)
        main_adv.setImageResource(resID)

        if(advIndex==advArr.size-1)advIndex=-1

    }


    override fun onResume() {
        Log.d(this.toString(),"onResume()")
        super.onResume()
    }

    override fun onPause() {
        Log.d(this.toString(),"onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.d(this.toString(),"onStop()")
        super.onStop()

        editor?.putString("id",EtLoginId)
        editor?.putString("pw",EtLoginPw)

    }

    override fun onDestroy() {
        Log.d(this.toString(),"onDestroy()")
        super.onDestroy()

        colorArr= emptyArray()
        advArr= emptyArray()
        editor?.remove("id")?.commit()
        editor?.remove("pw")?.commit()
        editorcupon?.remove("cupons")?.commit()

    }
}
