package com.example.gamexo_digio.util

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamexo_digio.HistoryActivity
import com.example.gamexo_digio.R
import kotlinx.android.synthetic.main.layout_recycler_history.view.*

class HistoryAdapter(private val user: ArrayList<HashMap<String, String?>>, private var hisClickListener: HistoryActivity) :
    RecyclerView.Adapter<HistoryAdapter.UserViewHolder>() {


    lateinit var getdatetime: String
    lateinit var getwinnwe: String

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_recycler_history, parent, false)
        )
    }

    override fun getItemCount() = user.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        getdatetime = user[position]["date_time"].toString()
        getwinnwe = user[position]["winner"].toString()


        holder.view.winner.text = "Winner :" + getwinnwe
        holder.view.date.text = "วันที่ :" + getdatetime

        holder.initialize(user[position])
    }

    class UserViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun initialize(item: HashMap<String, String?>) {

        }
    }

}

interface HistoryClickListener {
    fun onItemclick(item: HashMap<String, String?>, position: Int)
}