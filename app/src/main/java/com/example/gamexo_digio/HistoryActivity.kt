package com.example.gamexo_digio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.gamexo_digio.util.DialogUtils
import com.example.gamexo_digio.util.HistoryAdapter
import com.google.firebase.firestore.FirebaseFirestore

class HistoryActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private val db = FirebaseFirestore.getInstance()

    private var dialog = DialogUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialog.showProgress(this, "Please Wait..")
        setContentView(R.layout.activity_history)
        recyclerView = findViewById(R.id.recycler_view_his)
        this.parseItem()

        val buttonBack = findViewById<Button>(R.id.btn_home)
        buttonBack.setOnClickListener {
            val intent1 = Intent(this@HistoryActivity, MainActivity::class.java)
            startActivity(intent1)
        }
    }

    private fun parseItem(){
        db.collection("HistoryPlay")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    val list: ArrayList<HashMap<String, String?>> = ArrayList()
                    for (doc in task.result!!){
                        val date_time = doc.getString("date_time")
                        val winner = doc.getString("winner")
                        val item: HashMap<String, String?> = HashMap()
                        item["date_time"] = date_time
                        item["winner"] = winner
                        list.add(item)
                    }
                    recyclerView!!.visibility = View.VISIBLE
                    recyclerView!!.setHasFixedSize(true)
                    recyclerView!!.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                    val adapter = HistoryAdapter(list, this)
                    recyclerView!!.adapter = adapter

                    dialog.dialog.dismiss()
                }
            }
    }
}