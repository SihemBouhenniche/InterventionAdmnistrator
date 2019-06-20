package com.example.tdm2exo2.Fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tdm2exo2.InterventionAdapter
import com.example.tdm2exo2.R
import com.example.tdm2exo2.model.Intervention
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.BufferedReader
import java.io.File




class HomeFragment : Fragment() {
    companion object {
        var interventions:ArrayList<Intervention> = ArrayList<Intervention>()
    }


    var fileName = ""
    lateinit var interventionAdapter : InterventionAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.fileName = this.activity!!.cacheDir.absolutePath+"/InterventionJson.json"
        readJSONfromFile(fileName)
        interventionAdapter = InterventionAdapter(interventions,context!!)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater!!.inflate(R.layout.fragment_home, container, false)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        list_interventions.adapter = interventionAdapter
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