package com.example.tdm2exo2.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import com.example.tdm2exo2.R
import com.example.tdm2exo2.model.Intervention
import com.google.gson.Gson
import kotlinx.android.synthetic.main.add_fragment.*
import java.io.File
import java.lang.Integer.parseInt
import java.util.*


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
            var gson = Gson()
            val intervention = Intervention(
                parseInt(num_add.text.toString()),
                date,
                nom_add.text.toString(),
                type_add.selectedItem.toString()
            )
            HomeFragment.interventions.add(intervention)
            var jsonString:String = gson.toJson(HomeFragment.interventions)
            writeJSONtoFile(jsonString)
            Toast.makeText(this.context,"ajouté avec succés ",Toast.LENGTH_SHORT).show()
        }


    }}

    private fun writeJSONtoFile(jsonString:String) {
        //Get the file Location and name where Json File are get stored
        lateinit var file:File
        val fileName = this.activity!!.cacheDir.absolutePath + "/InterventionJson.json"
        if (!File(fileName).exists()) {
            file  = File(fileName)
        }


        file.writeText(jsonString)}


}