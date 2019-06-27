package com.example.tdm2exo2.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.room.Room
import com.example.tdm2exo2.R
import com.example.tdm2exo2.database.DataBase
import com.example.tdm2exo2.model.Intervention
import kotlinx.android.synthetic.main.add_fragment.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Integer.parseInt
import java.util.*
import kotlin.collections.ArrayList


class AddFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.add_fragment, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
           var date:String=""

        date_add.setOnDateChangeListener(CalendarView.OnDateChangeListener() {
                calendarView: CalendarView, i: Int, i1: Int, i2: Int ->
               val calendar = Calendar.getInstance()

                calendar.set(i, i1, i2)
           date=calendar.time.toString().removeRange(11,30)
            })

        add_btn.setOnClickListener {
        if ((num_add.text.toString().equals(""))|| (nom_add.text.toString().equals("")))
        {
            Toast.makeText(this.context,"remplissez toutes les valeurs",Toast.LENGTH_SHORT).show()
        }
            else
        {

            val intervention = Intervention(0,
                parseInt(num_add.text.toString()),
                date,
                nom_add.text.toString(),
                type_add.selectedItem.toString()
            )
            (HomeFragment.interventions as ArrayList<Intervention>).add(intervention)
            val db = Room.databaseBuilder(
                context!! ,
                DataBase::class.java, "intervention.db"
            ).build()

            GlobalScope.launch {

                    db.interventionDao().insertAll(intervention)

            }

            Toast.makeText(this.context,"ajouté avec succés ",Toast.LENGTH_SHORT).show()
        }


    }}




}