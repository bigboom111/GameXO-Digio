package com.example.gamexo_digio.database

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "HistoryPlay")
internal class HistoryPlay {
    @ColumnInfo(name = "Date_time")
    var Date_time = ""

    @ColumnInfo(name = "Winner")
    var winner = ""
}