package com.example.tdm2exo2.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tdm2exo2.R
import com.example.tdm2exo2.model.Intervention
import com.google.gson.Gson
import kotlinx.android.synthetic.main.add_fragment.*
import java.io.BufferedReader
import java.io.File
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
        add_btn.setOnClickListener {
            var gson = Gson()
            val intervention = Intervention(
                parseInt(num_add.text.toString()),
                Date(date_add.date),
                nom_add.text.toString(),
                type_add.selectedItem.toString()
            )
            HomeFragment.interventions.add(intervention)
            var jsonString:String = gson.toJson(HomeFragment.interventions)
            writeJSONtoFile(jsonString)
        }
    }

    private fun writeJSONtoFile(jsonString:String){
        //Get the file Location and name where Json File are get stored
        val fileName = this.activity!!.cacheDir.absolutePath+"/InterventionJson.json"
        val file= File(fileName)
        file.writeText(jsonString)
    }

}