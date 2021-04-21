package com.example.gamexo_digio

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.gamexo_digio.database.HistoryPlay
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class XOActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var buttons: Array<Array<Button?>>
    private var player1Turn = true
    private var num = 0
    private var roundCount = 0
    private var player1Points = 0
    private var player2Points = 0
    private var textViewPlayer1: TextView? = null
    private var textViewPlayer2: TextView? = null

    private val db = FirebaseFirestore.getInstance()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_x_o)
        val intent = intent
        val number = intent.getStringExtra("number")
        num = number!!.toInt()
        buttons = Array(
            num
        ) { arrayOfNulls<Button>(num) }
        textViewPlayer1 = findViewById(R.id.textView1)
        textViewPlayer2 = findViewById(R.id.textView2)
        val ly = findViewById<LinearLayout>(R.id.ly1)
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        for (i in 0 until num) {
            val row = LinearLayout(this)
            for (j in 0 until num) {
                val button = Button(this)
                val id = j + 1 + i * num
                button.id = id
                button.textSize = 30f
                row.addView(button)
            }
            layout.addView(row)
        }
        ly.addView(layout)
        for (i in 0 until num) {
            for (j in 0 until num) {
                val id = j + 1 + i * num
                buttons[i][j] = findViewById(id)
                buttons[i][j]?.setOnClickListener(this)
            }
        }
        val buttonReset = findViewById<Button>(R.id.btn_reset)
        buttonReset.setOnClickListener { resetGame() }
        val buttonBack = findViewById<Button>(R.id.btn_back)
        buttonBack.setOnClickListener {
            val intent1 = Intent(this@XOActivity, MainActivity::class.java)
            startActivity(intent1)
        }
    }

    override fun onClick(view: View) {
        if ((view as Button).text.toString() != "") {
            return
        }
        if (player1Turn) {
            view.text = "X"
        } else {
            view.text = "O"
        }
        roundCount++
        if (checkForWin()) {
            if (player1Turn) {
                player1Wins()
            } else {
                player2Wins()
            }
        } else if (roundCount == num * num) {
            draw()
        } else {
            player1Turn = !player1Turn
        }
    }

    private fun checkForWin(): Boolean {
        if (num == 3) {
            val field =
                Array(
                    num
                ) { arrayOfNulls<String>(num) }
            for (i in 0 until num) {
                for (j in 0 until num) {
                    field[i][j] = buttons[i][j]!!.text.toString()
                }
            }
            for (i in 0 until num) {
                if (field[i][0] == field[i][1] &&
                    field[i][0] == field[i][2] &&
                    field[i][0] != ""
                ) {
                    return true
                }
            }
            for (i in 0 until num) {
                if (field[0][i] == field[1][i] &&
                    field[0][i] == field[2][i] &&
                    field[0][i] != ""
                ) {
                    return true
                }
            }
            if (field[0][0] == field[1][1] &&
                field[0][0] == field[2][2] &&
                field[0][0] != ""
            ) {
                return true
            }
            if (field[0][2] == field[1][1] &&
                field[0][2] == field[2][0] &&
                field[0][2] != ""
            ) {
                return true
            }
        }
        if (num == 4) {
            val field =
                Array(
                    num
                ) { arrayOfNulls<String>(num) }
            for (i in 0 until num) {
                for (j in 0 until num) {
                    field[i][j] = buttons[i][j]!!.text.toString()
                }
            }
            for (i in 0 until num) {
                if (field[i][0] == field[i][1] &&
                    field[i][0] == field[i][2] &&
                    field[i][0] == field[i][3] &&
                    field[i][0] != ""
                ) {
                    return true
                }
            }
            for (i in 0 until num) {
                if (field[0][i] == field[1][i] &&
                    field[0][i] == field[2][i] &&
                    field[0][i] == field[3][i] &&
                    field[0][i] != ""
                ) {
                    return true
                }
            }
            if (field[0][0] == field[1][1] &&
                field[0][0] == field[2][2] &&
                field[0][0] == field[3][3] &&
                field[0][0] != ""
            ) {
                return true
            }
            if (field[0][3] == field[1][2] &&
                field[1][2] == field[2][1] &&
                field[3][0] == field[2][1] &&
                field[0][3] != ""
            ) {
                return true
            }
        }
        if (num == 5) {
            val field =
                Array(
                    num
                ) { arrayOfNulls<String>(num) }
            for (i in 0 until num) {
                for (j in 0 until num) {
                    field[i][j] = buttons[i][j]!!.text.toString()
                }
            }
            for (i in 0 until num) {
                if (field[i][0] == field[i][1] &&
                    field[i][0] == field[i][2] &&
                    field[i][0] == field[i][3] &&
                    field[i][0] == field[i][4] &&
                    field[i][0] != ""
                ) {
                    return true
                }
            }
            for (i in 0 until num) {
                if (field[0][i] == field[1][i] &&
                    field[0][i] == field[2][i] &&
                    field[0][i] == field[3][i] &&
                    field[0][i] == field[4][i] &&
                    field[0][i] != ""
                ) {
                    return true
                }
            }
            if (field[0][0] == field[1][1] &&
                field[0][0] == field[2][2] &&
                field[0][0] == field[3][3] &&
                field[0][0] == field[4][4] &&
                field[0][0] != ""
            ) {
                return true
            }
            if (field[0][4] == field[1][3] &&
                field[1][3] == field[2][2] &&
                field[2][2] == field[3][1] &&
                field[3][1] == field[4][0] &&
                field[0][4] != ""
            ) {
                return true
            }
        }
        if (num == 6) {
            val field =
                Array(
                    num
                ) { arrayOfNulls<String>(num) }
            for (i in 0 until num) {
                for (j in 0 until num) {
                    field[i][j] = buttons[i][j]!!.text.toString()
                }
            }
            for (i in 0 until num) {
                if (field[i][0] == field[i][1] &&
                    field[i][0] == field[i][2] &&
                    field[i][0] == field[i][3] &&
                    field[i][0] == field[i][4] &&
                    field[i][0] == field[i][5] &&
                    field[i][0] != ""
                ) {
                    return true
                }
            }
            for (i in 0 until num) {
                if (field[0][i] == field[1][i] &&
                    field[0][i] == field[2][i] &&
                    field[0][i] == field[3][i] &&
                    field[0][i] == field[4][i] &&
                    field[0][i] == field[5][i] &&
                    field[0][i] != ""
                ) {
                    return true
                }
            }
            if (field[0][0] == field[1][1] &&
                field[0][0] == field[2][2] &&
                field[0][0] == field[3][3] &&
                field[0][0] == field[4][4] &&
                field[0][0] == field[5][5] &&
                field[0][0] != ""
            ) {
                return true
            }
            if (field[0][5] == field[1][4] &&
                field[1][4] == field[2][3] &&
                field[2][3] == field[3][2] &&
                field[3][2] == field[4][1] &&
                field[4][1] == field[5][0] &&
                field[0][4] != ""
            ) {
                return true
            }
        }
        if (num == 7) {
            val field =
                Array(
                    num
                ) { arrayOfNulls<String>(num) }
            for (i in 0 until num) {
                for (j in 0 until num) {
                    field[i][j] = buttons[i][j]!!.text.toString()
                }
            }
            for (i in 0 until num) {
                if (field[i][0] == field[i][1] &&
                    field[i][0] == field[i][2] &&
                    field[i][0] == field[i][3] &&
                    field[i][0] == field[i][4] &&
                    field[i][0] == field[i][5] &&
                    field[i][0] == field[i][6] &&
                    field[i][0] != ""
                ) {
                    return true
                }
            }
            for (i in 0 until num) {
                if (field[0][i] == field[1][i] &&
                    field[0][i] == field[2][i] &&
                    field[0][i] == field[3][i] &&
                    field[0][i] == field[4][i] &&
                    field[0][i] == field[5][i] &&
                    field[0][i] == field[6][i] &&
                    field[0][i] != ""
                ) {
                    return true
                }
            }
            if (field[0][0] == field[1][1] &&
                field[0][0] == field[2][2] &&
                field[0][0] == field[3][3] &&
                field[0][0] == field[4][4] &&
                field[0][0] == field[5][5] &&
                field[0][0] == field[6][6] &&
                field[0][0] != ""
            ) {
                return true
            }
            if (field[0][6] == field[1][5] &&
                field[1][5] == field[2][4] &&
                field[2][4] == field[3][3] &&
                field[3][3] == field[4][2] &&
                field[4][2] == field[5][1] &&
                field[5][1] == field[6][0] &&
                field[0][6] != ""
            ) {
                return true
            }
        }
        return false
    }

    private fun player1Wins() {
        player1Points++
        val datetime = getDateTime()
        val formattedDateTime = formatDateTime(datetime)
        saveWinner(formattedDateTime , "Player X wins")
        updatePointsText()
        Toast.makeText(this, "Player X wins", Toast.LENGTH_LONG).show()
        val builder =
            AlertDialog.Builder(this@XOActivity)
        builder.setTitle("Winner")
        builder.setMessage("Player X wins")
        builder.setPositiveButton(
            "Play Again!"
        ) { dialogInterface, i -> resetBorad() }
        builder.setNegativeButton(
            "Home"
        ) { dialogInterface, i ->
            val intent = Intent(this@XOActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val alert = builder.create()
        alert.show()
    }

    private fun player2Wins() {
        player2Points++
        val datetime = getDateTime()
        val formattedDateTime = formatDateTime(datetime)
        saveWinner(formattedDateTime , "Player O wins")
        updatePointsText()
        Toast.makeText(this, "Player O wins", Toast.LENGTH_LONG).show()
        val builder =
            AlertDialog.Builder(this@XOActivity)
        builder.setTitle("Winner")
        builder.setMessage("Player O wins")
        builder.setPositiveButton(
            "Play Again!"
        ) { dialogInterface, i -> resetBorad() }
        builder.setNegativeButton(
            "Home"
        ) { dialogInterface, i ->
            val intent = Intent(this@XOActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val alert = builder.create()
        alert.show()
    }

    private fun draw() {
        val datetime = getDateTime()
        val formattedDateTime = formatDateTime(datetime)
        saveWinner(formattedDateTime , "Draw!")
        Toast.makeText(this, "Draw!", Toast.LENGTH_LONG).show()
        val builder =
            AlertDialog.Builder(this@XOActivity)
        builder.setTitle("Winner")
        builder.setMessage("Draw!")
        builder.setPositiveButton(
            "Play Again!"
        ) { dialogInterface, i -> resetBorad() }
        builder.setNegativeButton(
            "Home"
        ) { dialogInterface, i ->
            val intent = Intent(this@XOActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val alert = builder.create()
        alert.show()
    }

    private fun saveWinner(date_time : String , winner : String){
        val info = HistoryPlay()
        info.Date_time = date_time
        info.winner = winner

        db.collection("HistoryPlay").document()
            .set(info)
    }

    private fun updatePointsText() {
        textViewPlayer1!!.text = "Player X : $player1Points"
        textViewPlayer2!!.text = "Player O : $player2Points"
    }

    private fun resetBorad() {
        for (i in 0 until num) {
            for (j in 0 until num) {
                buttons[i][j]!!.text = ""
            }
        }
        roundCount = 0
        player1Turn = true
    }

    private fun resetGame() {
        player1Points = 0
        player2Points = 0
        updatePointsText()
        resetBorad()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("roundCount", roundCount)
        outState.putInt("player1Points", player1Points)
        outState.putInt("player2Points", player2Points)
        outState.putBoolean("player1Turn", player1Turn)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        roundCount = savedInstanceState.getInt("roundCount")
        player1Points = savedInstanceState.getInt("player1Points")
        player2Points = savedInstanceState.getInt("player2Points")
        player1Turn = savedInstanceState.getBoolean("player1Turn")
    }

    private fun getDateTime(): Date {
        return Date()
    }

    private fun formatDateTime(datetime: Date): String {
        val dateFormat: CharSequence = android.text.format.DateFormat.format("dd/MM/yy HH:mm:ss", datetime.time)
        return dateFormat.toString()
    }
}