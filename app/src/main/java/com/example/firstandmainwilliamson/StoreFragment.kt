package com.example.firstandmainwilliamson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

/**
 * A simple [Fragment] subclass.
 */
class StoreFragment : Fragment() {
    private var filtered = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_store, container, false)

        val model: MyViewModel = ViewModelProviders.of(this).get<MyViewModel>(MyViewModel::class.java)
        //val model: MyViewModel by viewModels()
        val recyclerView:RecyclerView = v.findViewById<RecyclerView>(R.id.storeRecyclerView)

        var storeList: List<StoreItem> = listOf()
        model.getStores().observe(viewLifecycleOwner, Observer { it->
            if (!filtered) {
                storeList = it
                (recyclerView.adapter as MyAdapter).updateList(storeList)
            }
        })

        recyclerView.layoutManager = LinearLayoutManager(this@StoreFragment.context)
        recyclerView.adapter = MyAdapter(storeList)

        val optionSelect = v.findViewById<Spinner>(R.id.storeFilter)
        optionSelect.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                //not an option
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val selectedItem: String = p0?.getItemAtPosition(p2)?.toString()!!
                val storeList = model.getStores().value ?: listOf()
                when (selectedItem) {
                    "Filter by Establishment" -> {
                        (recyclerView.adapter as MyAdapter).updateList(storeList)
                        filtered = false
                    }
                    "Dining & Bevs" -> {
                        val filteredStoreList = storeList.filter { it.type == "Dining & Bevs" }
                        filtered = true
                        (recyclerView.adapter as MyAdapter).updateList(filteredStoreList)
                    }
                    "Shops & Services" -> {
                        val filteredStoreList = storeList.filter { it.type == "Shops & Services" }
                        filtered = true
                        (recyclerView.adapter as MyAdapter).updateList(filteredStoreList)
                    }
                    "Entertainment" -> {
                        val filteredStoreList = storeList.filter { it.type == "Entertainment" }
                        filtered = true
                        (recyclerView.adapter as MyAdapter).updateList(filteredStoreList)
                    }
                }
            }

        }

        return v
    }

    private inner class MyAdapter (private var storeList: List<StoreItem>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        fun updateList(newList: List<StoreItem>) {
            storeList = newList
            notifyDataSetChanged()
        }

        inner class MyViewHolder (val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.store_card_view, parent, false)
            return MyViewHolder(v)
        }

        override fun getItemCount(): Int {
            return storeList.size
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val storeName = holder.view.findViewById<TextView>(R.id.storeName)
            val storeDescription = holder.view.findViewById<TextView>(R.id.description)
            storeName.text = storeList[position].name
            storeDescription.text = storeList[position].description
            Glide.with(this@StoreFragment)
                .load(storeList[position].pictureUrl)
                .apply(RequestOptions().override(192, 192))
                .into(holder.view.findViewById(R.id.poster))
        }

    }
}
