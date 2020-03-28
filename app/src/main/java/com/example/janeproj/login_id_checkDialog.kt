package com.example.janeproj

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.dialog_login_id_check.*

class login_id_checkDialog : AppCompatActivity() {
    var tmpString:String?=""

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(this.toString(),"onCreate()")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_login_id_check)


        if(intent.hasExtra("logincheckmsg")){
            if(intent.hasExtra("loginidlen")){

                tmpString="현재 길이: "+intent.getStringExtra("loginidlen")
                tmpString=tmpString+"\n"+intent.getStringExtra("logincheckmsg")


                Log.d(this.toString(),tmpString)

                tv_dialog_login_title.setText(tmpString)
            }

        }

        btn_dialog_login_ok.setOnClickListener{
           finish()

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

        tmpString=null
        super.onDestroy()
    }
}