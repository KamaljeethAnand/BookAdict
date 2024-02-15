package com.example.bookadict

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var s1: Button
    private lateinit var s2: Button
    private lateinit var s3: Button
    private lateinit var s4: Button
    private lateinit var s5: Button

    private lateinit var c1: Button
    private lateinit var c2: Button
    private lateinit var c3: Button
    private lateinit var c4: Button
    private lateinit var c5: Button

    private lateinit var w1: Button
    private lateinit var w2: Button
    private lateinit var w3: Button
    private lateinit var w4: Button
    private lateinit var w5: Button

    private lateinit var p1: Button
    private lateinit var p2: Button
    private lateinit var p3: Button
    private lateinit var p4: Button
    private lateinit var p5: Button

    private lateinit var o1: Button
    private lateinit var o2: Button
    private lateinit var o3: Button
    private lateinit var o4: Button
    private lateinit var o5: Button
    private var isAdmin:Boolean = false
    private lateinit var adminbtn: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isAdmin= intent.extras?.getBoolean("admin") == true
        s1 =findViewById(R.id.ss1)
        s2=findViewById(R.id.ss2)
        s3=findViewById(R.id.ss3)
        s4=findViewById(R.id.ss4)
        s5=findViewById(R.id.ss5)

        c1=findViewById(R.id.cg1)
        c2=findViewById(R.id.cg2)
        c3=findViewById(R.id.cg3)
        c4=findViewById(R.id.cg4)
        c5=findViewById(R.id.cg5)

        w1=findViewById(R.id.wt1)
        w2=findViewById(R.id.wt2)
        w3=findViewById(R.id.wt3)
        w4=findViewById(R.id.wt4)
        w5=findViewById(R.id.wt5)

        p1=findViewById(R.id.pe1)
        p2=findViewById(R.id.pe2)
        p3=findViewById(R.id.pe3)
        p4=findViewById(R.id.pe4)
        p5=findViewById(R.id.pe5)

        o1=findViewById(R.id.oe1)
        o2=findViewById(R.id.oe2)
        o3=findViewById(R.id.oe3)
        o4=findViewById(R.id.oe4)
        o5=findViewById(R.id.oe5)

        adminbtn =findViewById(R.id.adminbtn)

        s1.setOnClickListener{
//            val intent=Intent(this,ssmod1::class.java)
//            startActivity(intent)
            val intent=Intent(this,PDFListActivity::class.java)
            startActivity(intent)
        }
        s2.setOnClickListener{
            val intent=Intent(this,ssmod2::class.java)
            startActivity(intent)
        }
        s3.setOnClickListener{
            val intent=Intent(this,ssmod3::class.java)
            startActivity(intent)
        }
        s4.setOnClickListener{
            val intent=Intent(this,ssmod4::class.java)
            startActivity(intent)
        }
        s5.setOnClickListener{
            val intent=Intent(this,ssmod5::class.java)
            startActivity(intent)
        }
        c1.setOnClickListener{
            val intent=Intent(this,ssmod5::class.java)
            startActivity(intent)
        }
        adminbtn.visibility=View.GONE
        if(isAdmin)
            adminbtn.visibility= View.VISIBLE
        adminbtn.setOnClickListener {

            val intent=Intent(this,admin::class.java)
            startActivity(intent)
        }








    }
}