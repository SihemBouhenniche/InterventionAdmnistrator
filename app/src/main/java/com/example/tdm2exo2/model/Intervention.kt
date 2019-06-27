package com.example.tdm2exo2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity  (tableName = "intervention")
data class Intervention(
    @PrimaryKey (autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "numero") var num: Int,
    @ColumnInfo(name = "date") var date : String,
    @ColumnInfo(name = "nom") var nom : String,
    @ColumnInfo(name = "type") var type : String

)