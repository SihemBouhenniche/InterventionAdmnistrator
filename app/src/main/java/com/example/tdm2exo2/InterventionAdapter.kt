package com.example.tdm2exo2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.tdm2exo2.Fragments.HomeFragment
import com.example.tdm2exo2.model.Intervention
import com.google.gson.Gson
import kotlinx.android.synthetic.main.card_intervention.view.*
import java.io.File

class InterventionAdapter : BaseAdapter{
    var list : ArrayList<Intervention> = ArrayList<Intervention>()
    lateinit var context : Context

    constructor(list:ArrayList<Intervention>,context: Context){
        this.context = context
        this.list = list
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = list.get(position)
        var inflator = context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutItem = inflator.inflate(R.layout.card_intervention,null)
        //layoutItem.cardTitle.text = item.type!!
        layoutItem.num.text = item.num.toString()
        layoutItem.nom.text = item.nom
        layoutItem.date.text = item.date.toString()
        layoutItem.type.text = item.type
        layoutItem.delete_btn.setOnClickListener{

            var gson = Gson()
            var jsonString:String = gson.toJson(this.list)
            writeJSONtoFile(jsonString)
            this.list.removeAt(position)
            notifyDataSetChanged()
        }
        return layoutItem
    }
    private fun writeJSONtoFile(jsonString:String){
        //Get the file Location and name where Json File are get stored
        val fileName = this.context.cacheDir.absolutePath+"/InterventionJson.json"
        val file= File(fileName)
        file.writeText(jsonString)
    }
    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }
}