package com.example.tdm2exo2

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Filter
import android.widget.Filterable
import com.example.tdm2exo2.model.Intervention
import com.google.gson.Gson
import kotlinx.android.synthetic.main.card_intervention.view.*
import java.io.File
import java.util.*

class InterventionAdapter : BaseAdapter,Filterable{
    var list : ArrayList<Intervention> = ArrayList<Intervention>()
    var filteredList = java.util.ArrayList<Intervention>()
    lateinit var context : Context
    var listFilter : AnonceFilter? = null

    constructor(list:ArrayList<Intervention>,context: Context){
        this.context = context
        this.list=list
        this.filteredList = list

    }
    override fun getFilter(): Filter? {
        if (listFilter == null) {
            listFilter= AnonceFilter()
        }

        return listFilter

    }
    init{

    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val item = filteredList.get(position)
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
    override fun getItem(position: Int): Any {  if (filteredList!=null)
    { return filteredList.get(position)}
    else
    {
        return list.get(position)
    }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        if (filteredList!=null)
        { return filteredList.size}
        else
        {
            return list.size
        }
    }


    inner  class AnonceFilter : Filter() {

        @Override
        override fun  performFiltering(constraint: CharSequence): FilterResults {
            var  filterResults: FilterResults =  FilterResults ()
            if (constraint!=null && constraint.length>0) {

                var  tempList: java.util.ArrayList<Intervention> = java.util.ArrayList<Intervention>();

                // search content in friend list
                for (intervention : Intervention in list ) {

                    if (intervention.date.equals(constraint.toString())) {
                        Log.i("stoed","${intervention.date.toString()}")
                        Log.i("slected","${constraint.toString()}")
                        tempList.add(intervention)




                    }
                }

                filterResults.count = tempList.size
                filterResults.values = tempList

            } else {
                filterResults.count = list.size
                filterResults.values = list
            }

            return filterResults;
        }

        /**
         * Notify about filtered list to ui
         * @param constraint text
         * @param results filtered result
         */
        @SuppressWarnings("unchecked")
        @Override
        override fun  publishResults(constraint:CharSequence, results: FilterResults) {
            filteredList = results.values  as java.util.ArrayList<Intervention>

            notifyDataSetChanged();
        }
    }



}

