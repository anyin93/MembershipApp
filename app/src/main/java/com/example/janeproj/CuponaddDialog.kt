package com.example.janeproj

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_msgdialog.*
import com.google.gson.GsonBuilder as GsonBuilder

class CuponaddDialog : AppCompatActivity() {

    var pref: SharedPreferences?=null
    var editor: SharedPreferences.Editor?= null

    var name:String?=null
    var date:String?=null
    var store:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(this.toString(),"onCreate()")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_msgdialog)

        pref=this.getSharedPreferences("cupon",0)
        editor=pref?.edit()

        if(intent.hasExtra("date")){
            if(intent.hasExtra("store")) {
                if (intent.hasExtra("name")) {
                    tv_dialog_login_title.text = "쿠폰이 등록되었습니다."
                    name = intent.getStringExtra("name")
                    date = intent.getStringExtra("date")
                    store = intent.getStringExtra("store")
                } else {
                    tv_dialog_login_title.text = "쿠폰이름을 입력해주세요."
                }
            }
        }

        btn_dialog_login_ok.setOnClickListener{

            if(name!=null){
                intent= Intent(this,CuponActivity::class.java)

                //저장 : SharedPreference에서 원래 있던 배열 빼와서
                //새로운 쿠폰 객체 추가하고
                //그것을 다시 json으로 변환해서 SharedPreference에 저장
                val couponType = object : TypeToken<List<Cupon>>() {}.type
                var gson=GsonBuilder().create()
                var jsoncupons= pref?.getString("cupons",null)

                Log.d(this.toString(), jsoncupons.toString())

               var gsoncupons= mutableListOf<Cupon>()

                if(jsoncupons==null){
                    Log.d(this.toString(), "null임")

                    gsoncupons.add(Cupon(name!!, date!!, store!!, true,false))
                }else{
                    Log.d(this.toString(), "null아님")

                    gsoncupons = gson.fromJson<MutableList<Cupon>>(jsoncupons, couponType)
                    gsoncupons.add(Cupon(name!!, date!!, store!!, true,false))

                }

                Log.d(this.toString(), gsoncupons.toString())

                jsoncupons = gson.toJson(gsoncupons)

                Log.d(this.toString(), jsoncupons.toString())
                editor?.putString("cupons", jsoncupons.toString())?.let{
                    Log.d(this.toString(), "넣기 성공")
                    editor?.commit()

                }


                startActivity(intent)
                finish()
            }

            else{

                finish()
            }
        }



    }
}
