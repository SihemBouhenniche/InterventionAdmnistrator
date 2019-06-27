package com.example.tdm2exo2.Fragments

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tdm2exo2.InterventionAdapter
import com.example.tdm2exo2.database.DataBase
import com.example.tdm2exo2.model.Intervention
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*






class HomeFragment : Fragment(), DatePickerDialog.OnDateSetListener {


    companion object {
        var interventions: List<Intervention> = ArrayList<Intervention>()
        lateinit var db:RoomDatabase

    }

    var fileName = ""

    lateinit var interventionAdapter : InterventionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = Room.databaseBuilder(
            context!! ,
            DataBase::class.java, "intervention.db"
        ).build()
GlobalScope.launch {
    //(db as DataBase).interventionDao().insertAll(Intervention(0, 25,"Content","gg","gg"))
    interventions = (db as DataBase).interventionDao().getAll()



    }

        interventionAdapter = InterventionAdapter(interventions as ArrayList<Intervention>,context!!)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(com.example.tdm2exo2.R.layout.fragment_home, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        list_interventions.adapter = interventionAdapter
        list_interventions.isTextFilterEnabled=true

        val datePickerDialog = DatePickerDialog(
            context, this,2018,1,1
        )

        search_btn.setOnClickListener {

            datePickerDialog.show();
        }
    }


    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {


        val calendar = Calendar.getInstance()

        calendar.set(year, month, dayOfMonth)


        val date=calendar.time.toString().removeRange(11,30)
        if (TextUtils.isEmpty(date)) {
            list_interventions.clearTextFilter();
        }
        else {
            list_interventions.setFilterText(date);
        }
    }
}


