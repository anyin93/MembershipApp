package com.example.janeproj

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CompoundButton
import android.widget.Toast
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_endcupon.view.*
import kotlinx.android.synthetic.main.item_cupon.view.*

import kotlin.collections.ArrayList
import kotlin.math.log

class CuponListAdapter(val context:Context, val cuponList: MutableList<Cupon>) : BaseAdapter() {

    var pref: SharedPreferences?=null
    var editor: SharedPreferences.Editor?= null

    interface ListBtnClickListener{
        fun onListBtnClick(position:Int)
    }



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        //LayoutInflager는 item을 Adapter에서 View로 부풀려주는 역할
        val view:View=LayoutInflater.from(context).inflate(R.layout.item_cupon,null)

        //ArrayList<Cupon>의 변수의 이미지와 데이터를 ImageView와 TextView에 담는다.
        val cupon=cuponList[position]
        val resourceId=context.resources.getIdentifier("cuponicon","drawable",context.packageName)

        val gson=GsonBuilder().create()
        var gsonType=object :TypeToken<MutableList<Cupon>>(){}.type
        var gsoncupon= mutableListOf<Cupon>()

        pref=context.getSharedPreferences("cupon",0)
        editor=pref?.edit()

        view.iv_item_icon.setImageResource(resourceId)
        view.tv_item_title.text=cupon.cupon_name
        view.tv_item_val.text=cupon.cupon_valid
        view.tv_item_store.text=cupon.cupon_store


        //Toast.makeText(context,position.toString()+"번째",Toast.LENGTH_SHORT).show()

        //만료 쿠폰 처리
        if(cupon.cupon_end==false) {
            view.iv_item_endback.visibility = View.VISIBLE
            view.tv_item_endback.visibility = View.VISIBLE
            view.btn_endcupon_delete.visibility = View.VISIBLE
            view.sw_cupon_enjoy_fav.visibility=View.INVISIBLE


            view.btn_endcupon_delete.setOnClickListener{

                cuponList.removeAt(position)
                notifyDataSetChanged()

                var jsoncupon=pref!!.getString("endcupons",null)

                jsoncupon?.let {
                    var gsoncupon=gson.fromJson<MutableList<Cupon>>(jsoncupon,gsonType)
                    gsoncupon.removeAt(position)
                    jsoncupon=gson.toJson(gsoncupon)
                    editor!!.putString("endcupons",jsoncupon).commit()
                }
            }



        //정상 쿠폰 처리
        }else {

            //스위치 클릭시
            view.sw_cupon_enjoy_fav.setOnCheckedChangeListener { compoundButton: CompoundButton, b: Boolean ->
                var jsoncupon=pref!!.getString("cupons",null)

                jsoncupon?.let {
                    gsoncupon=gson.fromJson<MutableList<Cupon>>(jsoncupon,gsonType)

                    if (b == true) {
                        Toast.makeText(context,"즐겨찾기 설정",Toast.LENGTH_SHORT).show()
                        gsoncupon[position].cupon_fav=true
                    } else {
                        Toast.makeText(context,"즐겨찾기 해제",Toast.LENGTH_SHORT).show()
                        gsoncupon[position].cupon_fav=false
                    }
                    jsoncupon=gson.toJson(gsoncupon)
                    editor!!.putString("cupons",jsoncupon).commit()



                }


            }

            var jsoncupon=pref!!.getString("cupons",null)
            gsoncupon=gson.fromJson<MutableList<Cupon>>(jsoncupon,gsonType)

            //스위치 보여주기 설정
            if(gsoncupon[position].cupon_fav==true){
                view.sw_cupon_enjoy_fav.isChecked=true
            }else{
                view.sw_cupon_enjoy_fav.isChecked=false
            }

        }
                return view


    }

    override fun getItem(position: Int): Any {
       return cuponList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return cuponList.size
    }




}