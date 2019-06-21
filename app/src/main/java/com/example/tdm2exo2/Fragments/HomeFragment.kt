package com.example.tdm2exo2.Fragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.example.tdm2exo2.InterventionAdapter
import com.example.tdm2exo2.model.Intervention
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.BufferedReader
import java.io.File
import java.util.*






class HomeFragment : Fragment(), DatePickerDialog.OnDateSetListener {
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

    companion object {
        var interventions:ArrayList<Intervention> = ArrayList<Intervention>()
    }


    var fileName = ""
    lateinit var interventionAdapter : InterventionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    this.fileName = this.activity!!.cacheDir.absolutePath+"/InterventionJson.json"

        var file :File= File(fileName);
        if(file.exists())
        {
            readJSONfromFile(fileName)

        }
       interventionAdapter = InterventionAdapter(interventions,context!!)

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

    private fun readJSONfromFile(f:String) {

        //Creating a new Gson object to read data
        var gson = Gson()
        //Read the PostJSON.json file
        val bufferedReader: BufferedReader = File(f).bufferedReader()
        // Read the text from buffferReader and store in String variable
        bufferedReader.forEachLine {

            interventions = gson.fromJson(
                it,
                object : TypeToken<ArrayList<Intervention>>() {

                }.type
            ) as ArrayList<Intervention>
        }
    }
    }

