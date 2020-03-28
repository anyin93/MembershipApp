package com.example.janeproj

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_cupon.*
import java.util.*

class CuponActivity : AppCompatActivity() {
    val cuponType=object :TypeToken<List<Cupon>>(){}.type //gson타입

    var calendar:Calendar?=null
    var pref: SharedPreferences?=null
    var editor: SharedPreferences.Editor?= null

    var y=0
    var m=0
    var d=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cupon)
        Log.d(this.toString(),"onCreate()")


        //데이터 넣어주기
//        cuponList.add(Cupon("애플망고 음료 구매시, 카페 아메리카노R 1잔 증정",
//            "2020-05-20","부천중동점",true))
//        cuponList.add(Cupon("봄 시즌 음료 구매시, 프리미엄 블렌드 카페라테R 증정",
//            "2020-01-30","부천송내점",true))
//        cuponList.add(Cupon("아메리카노R FREE 쿠폰",
//            "2020-06-15","강남구청점",true))
//        cuponList.add(Cupon("겨울 음료 구매시, 초코 쿠키 1개 증정",
//            "2020-02-01","신연수점",true))
//        cuponList.add(Cupon("텀블러 1개 무료 증정",
//            "2020-05-25","신논현점",true))

        pref=this.getSharedPreferences("cupon",0)
        editor=pref?.edit()
        calendar= Calendar.getInstance()
        y=calendar!!.get(Calendar.YEAR)
        m=calendar!!.get(Calendar.MONTH)+1
        d=calendar!!.get(Calendar.DAY_OF_MONTH)

        Log.d(this.toString(),y.toString()+m.toString()+d.toString())

        //SharedPreference에서 쿠폰리스트 꺼내기
        var gson=GsonBuilder().create()

        var cuponjson=pref?.getString("cupons",null)
        var endcuponjson=pref?.getString("endcupons",null)

        Log.d(this.toString(),"테스트 cuponjson")
        Log.d(this.toString(),cuponjson.toString())
        Log.d(this.toString(),endcuponjson.toString())

        //var parser=JsonParser()


        cuponjson?.let {
            var cupongson= mutableListOf<Cupon>()
            cupongson=gson.fromJson<MutableList<Cupon>>(cuponjson,cuponType)
            var endcupongson = mutableListOf<Cupon>()

            endcuponjson?.let {
                endcupongson = gson.fromJson<MutableList<Cupon>>(endcuponjson, cuponType)
            }


                var realcupon= mutableListOf<Cupon>()

                for(c in cupongson){
                    var str=c.cupon_valid
                    var strarr=str.split("-")

                    if(strarr[0].toInt()<y){
                        c.cupon_end=false
                        endcupongson.add(c)
                    }else if(strarr[0].toInt()==y ){
                        if(strarr[1].toInt()<m){
                            c.cupon_end=false
                            endcupongson.add(c)

                        }else if(strarr[1].toInt()==m && strarr[2].toInt()<d) {
                            c.cupon_end=false
                            endcupongson.add(c)
                        }else{
                            c.cupon_end=true
                            realcupon.add(c)
                        }
                    }
                    else{
                        realcupon.add(c)
                    }
                }

                endcuponjson=gson.toJson(endcupongson)
                editor!!.putString("endcupons",endcuponjson).commit()
                Log.d(this.toString(),"만료: "+endcuponjson.toString())

                cuponjson=gson.toJson(realcupon)
                editor!!.putString("cupons",cuponjson).commit()
                Log.d(this.toString(),"가능: "+cuponjson.toString())

                val adapter=CuponListAdapter(this,realcupon)
                lv_cupon_list.adapter=adapter
            }





        btn_cupon_endcupon.setOnClickListener{
            intent=Intent(this,EndcuponActivity::class.java)
            startActivity(intent)
            finish()

        }
        btn_cupon_addcupon.setOnClickListener{
            intent=Intent(this,AddcuponActivity::class.java)
            startActivity(intent)
            finish()

        }

        btn_cupon_back.setOnClickListener{
            intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }
        btn_cupon_home.setOnClickListener{
            intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

    override fun onStart() {
        super.onStart()
        Log.d(this.toString(),"onStart()")

        if(intent.hasExtra("addname")){
            val addname=intent.getStringExtra("addname")
            val adddate=intent.getStringExtra("adddate")
            val addstore=intent.getStringExtra("addstore")



        }
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
