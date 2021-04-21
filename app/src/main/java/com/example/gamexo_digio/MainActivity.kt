package com.example.gamexo_digio

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun playGame(view: View?) {
        val num = findViewById<View>(R.id.txt_number) as EditText
        if (num.text.toString().trim { it <= ' ' } == "") {
            num.error = "...."
        } else {
            val number = num.text.toString()
            val n = number.toInt()
            if (n < 3 || n > 7) {
                num.error = "เริ่มตั้งแต่ 3 - 7"
            } else {
                val intent = Intent(this@MainActivity, XOActivity::class.java)
                intent.putExtra("number", number)
                startActivity(intent)
            }
        }
    }

    fun historyPlay(view: View?){
        val intent = Intent(this@MainActivity, HistoryActivity::class.java)
        startActivity(intent)
    }
}