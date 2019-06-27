package com.example.tdm2exo2.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tdm2exo2.model.Intervention

@Database(
    entities = [Intervention::class],
    version = 1,
    exportSchema = false
)
abstract class DataBase : RoomDatabase(){
    abstract fun interventionDao(): InterventionDao


    companion object {
        @Volatile private var instance: DataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            DataBase::class.java, "intervention.db")
            .build()
    }
}