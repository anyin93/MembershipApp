package com.example.janeproj
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*


class loginActivity  : AppCompatActivity() {

    var IdCheckFlag:Boolean?=false
    var EtLoginIdSize:Int?=null
    var EtLoginText:String?=null
    var EtLoginPw:String?=null
    var EtLoginId:String?=null

    var pref: SharedPreferences?=null
    var editor: SharedPreferences.Editor?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(this.toString(),"onCreate()")
        setContentView(R.layout.activity_login)

        btn_login_send.setOnClickListener{
            Toast.makeText(this,"로그인체크",Toast.LENGTH_SHORT).show()


            EtLoginId =et_login_id.text.toString()
            EtLoginPw=et_login_pw.text.toString()
            EtLoginIdSize= EtLoginId!!.length

            pref=this.getSharedPreferences("session",0)
            editor=pref?.edit()

            Log.d(this.toString(),"아이디 체크 시작")
            if(EtLoginIdSize!! <3){
                intent=Intent(this,login_id_checkDialog::class.java)
                EtLoginText="아이디를 3글자 이상 입력해주세요."
                if(EtLoginIdSize!!<=0){
                    EtLoginText="아이디를 입력해주세요."

                }
                intent.putExtra("logincheckmsg",EtLoginText)
                intent.putExtra("loginidlen",EtLoginIdSize.toString())


                startActivity(intent);
            }

            //preferences 인스턴스 저장, 0:Context.MODE_PRIVATE
            //에디터를 호출해 editor로 초기화
           else{

                Log.d(this.toString(),EtLoginId+"     "+EtLoginPw)

                finish()

                intent=Intent(this,MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent)


            }


        }


        btn_login_join.setOnClickListener{
            intent=Intent(this,JoinActivity::class.java)
            startActivity(intent)
        }


        btn_login_findid.setOnClickListener{
            intent=Intent(this,FindIdActivity::class.java)
            startActivity(intent)
        }

        btn_login_back.setOnClickListener{
            finish()
        }
        btn_login_home.setOnClickListener{
            //새로 시작하려는 액티비티가 이전에 있을 경우 그 위에 모든 액티비티 다 지우고 새로 시작.
            intent = Intent(applicationContext, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }


    }

    override fun onStart() {
        super.onStart()
        Log.d(this.toString(),"onStart()")

//시작시마다 id,pw 초기화(다시시작해도 초기화될 수 있음)
        et_login_id.setText("")
        et_login_pw.setText("")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(this.toString(),"onReStart()")

    }


    override fun onResume() {
        super.onResume()
        Log.d(this.toString(),"onResume()")

        if(IdCheckFlag==true){
            et_login_id.setText("")
            et_login_pw.setText("")
            IdCheckFlag=false
        }

    }

    override fun onPause() {
        super.onPause()

        Log.d(this.toString(),"onPause()")
        if(EtLoginText!=null){
            IdCheckFlag=true

        }

        editor?.putString("id",EtLoginId)
        editor?.putString("pw",EtLoginPw)
        editor?.commit()

    }

    override fun onStop() {
        Log.d(this.toString(),"onStop()")
        super.onStop()


    }

    override fun onDestroy() {
        Log.d(this.toString(),"onDestroy()")
        super.onDestroy()

        //리소스 해제
        IdCheckFlag=null
        EtLoginId=null
        EtLoginIdSize=null
        EtLoginText=null
        EtLoginPw=null

    }
}
