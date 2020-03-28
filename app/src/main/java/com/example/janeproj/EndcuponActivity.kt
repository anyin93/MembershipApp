package com.example.janeproj

import android.content.Intent
import android.content.SharedPreferences
import com.google.gson.reflect.TypeToken

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_endcupon.*
import com.example.janeproj.CuponListAdapter as CuponListAdapter

class EndcuponActivity : AppCompatActivity() {
    var endCuponList= mutableListOf<Cupon>()
    var pref: SharedPreferences?=null
    var editor: SharedPreferences.Editor?= null
    var adapter: CuponListAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_endcupon)

        //데이터 넣어주기
//        endCuponList.add(Cupon("애플망고 음료 구매시, 카페 아메리카노R 1잔 증정",
//            "2020-05-20","부천중동점",false))
//        endCuponList.add(Cupon("봄 시즌 음료 구매시, 프리미엄 블렌드 카페라테R 증정",
//            "2020-01-30","부천송내점",false))
//        endCuponList.add(Cupon("아메리카노R FREE 쿠폰",
//            "2020-06-15","강남구청점",false))
        pref=this.getSharedPreferences("cupon",0)
        editor=pref?.edit()


        var gson=GsonBuilder().create()
        var gsonType=object :TypeToken<MutableList<Cupon>>(){}.type
        var jsonEndcupon=pref!!.getString("endcupons",null)

        jsonEndcupon?.let{
            var gsonEndcupon=gson.fromJson<MutableList<Cupon>>(jsonEndcupon,gsonType)
            endCuponList=gsonEndcupon
        }



        adapter= CuponListAdapter(this,endCuponList)
        lv_Endcupon_list.adapter=adapter

        btn_endcupon_back.setOnClickListener{
            finish()

        }
        btn_endcupon_home.setOnClickListener{
            intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }
        btn_cupon_cupon.setOnClickListener{
            intent=Intent(this,CuponActivity::class.java)
            startActivity(intent)
            finish()

        }
        btn_cupon_addcupon.setOnClickListener{
            intent=Intent(this,AddcuponActivity::class.java)
            startActivity(intent)
            finish()

        }

//        lv_Endcupon_list.setOnItemClickListener{
//            parent,view,position,id->
//            val selectecItem=view as TextView
//            Toast.makeText(this,"클릭",Toast.LENGTH_SHORT).show()
//
//            Toast.makeText(this,selectecItem.text,Toast.LENGTH_SHORT).show()
//        }




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

        super.onDestroy()
    }

}
