package com.yupaopao.android.fastclick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.yupaopao.android.debounce.Except
import com.yupaopao.android.debounce.FastClick
import com.yupaopao.android.debounce.SingleClick
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        btn_click.setOnClickListener(object : View.OnClickListener {
//            @Except
//            override fun onClick(p0: View?) {
//                Log.i("AspectFastClick", "quick onclick")
//            }
//        })
        btn_click.setOnClickListener(object : View.OnClickListener {
            @SingleClick
            override fun onClick(p0: View?) {
                Log.i("AspectFastClick", "quick onclick")
            }
        })
    }
}
