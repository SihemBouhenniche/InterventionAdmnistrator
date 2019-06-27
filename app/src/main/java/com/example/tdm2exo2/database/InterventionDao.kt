package com.example.tdm2exo2.database

import androidx.room.*
import com.example.tdm2exo2.model.Intervention

@Dao
interface InterventionDao
{


        @Query("SELECT * FROM intervention")
        fun getAll(): List<Intervention>



        @Insert
        fun insertAll(vararg intervention: Intervention)

        @Delete
        fun delete(intervention: Intervention)

        @Update
        fun update(vararg intervention: Intervention)


}