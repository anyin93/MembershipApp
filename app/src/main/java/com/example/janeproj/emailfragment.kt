package com.example.janeproj

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


class emailfragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(this.toString(),"onCreate()")

        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view=inflater.inflate(R.layout.fragment_email,null)

        return view
    }
    override fun onStart() {
        super.onStart()
        Log.d(this.toString(),"onStart()")
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
