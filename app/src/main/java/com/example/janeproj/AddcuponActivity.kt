package com.example.janeproj

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_addcupon.*
import java.util.*

class AddcuponActivity : AppCompatActivity() {

    var storeList= arrayOf("강남구청점","신논현점","부천중동점","신연수점","원미구청점")

    var store:String?=null
    var name:String?=null
    var date:String?=null
    var y =0
    var m=0
    var d=0

    var calendar:Calendar?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addcupon)
        Log.d(this.toString(),"onCreate()")


        calendar=Calendar.getInstance()

        y=calendar!!.get(Calendar.YEAR)
        m=calendar!!.get(Calendar.MONTH)
        d=calendar!!.get(Calendar.DAY_OF_MONTH)

        //spinner 어답터 설정
        spn_addcupon_store.adapter=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,storeList)
        spn_addcupon_store.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(applicationContext,"가능 매장을 선택하세요.",Toast.LENGTH_LONG).show()

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                store=storeList[position]
            }

        }

        btn_addcupon_val.setOnClickListener{

            Log.d(this.toString(),y.toString()+m.toString()+d.toString())
            //달력 리스너 생성
            var dateListener=object :DatePickerDialog.OnDateSetListener{
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    date="${year}-${month+1}-${dayOfMonth}"
                    tv_addcupon_date.text=date
                    tv_addcupon_date.visibility= view!!.visibility
                }
            }
            //달력 다이얼로그 띠우기
            DatePickerDialog(this,dateListener,y,m,d)
                .show()


        }
        btn_addcupon_back.setOnClickListener{
            finish()
        }
        btn_addcupon_home.setOnClickListener{
            intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()

        }
        btn_addcupon_cupon.setOnClickListener{
            intent= Intent(this,CuponActivity::class.java)
            startActivity(intent)
            finish()

        }
        btn_addcupon_endcupon.setOnClickListener{
            intent= Intent(this,EndcuponActivity::class.java)
            startActivity(intent)
            finish()

        }
        btn_addcupon_ok.setOnClickListener{
            name=et_addcupon_name.text.toString()


            //등록
            intent=Intent(this,CuponaddDialog::class.java)

            if(name!=""){
                intent.putExtra("name",name)

            }
            intent.putExtra("date",date)
            intent.putExtra("store",store)

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
        et_addcupon_name.setText("")
        tv_addcupon_date.visibility=View.INVISIBLE

        y=calendar!!.get(Calendar.YEAR)
        m=calendar!!.get(Calendar.MONTH)
        d=calendar!!.get(Calendar.DAY_OF_MONTH)
        date="${y}-${m+1}-${d}"
        tv_addcupon_date.text=date
        tv_addcupon_date.visibility= View.VISIBLE
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
