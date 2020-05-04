package com.example.firstandmainwilliamson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 */
class StoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_store, container, false)

        val storeList = generateStores()
        val recyclerView:RecyclerView = v.findViewById<RecyclerView>(R.id.storeRecyclerView).apply {
            layoutManager = LinearLayoutManager(this@StoreFragment.context)
            adapter = MyAdapter(storeList)
        }

        return v
    }

    private fun generateStores(): List<StoreItem> {
        val storeList = mutableListOf<StoreItem>()

        storeList.add(StoreItem(1, "Sports Clips", "Hair Salon"))
        storeList.add(StoreItem(2, "Anytime Fitness", "Gym"))
        storeList.add(StoreItem(3, "Joseph A Bank", "Clothing"))
        storeList.add(StoreItem(4, "Matt Hagan Outdoors", "Outdoor"))
        storeList.add(StoreItem(5, "Alumni Hall", "Clothing"))
        storeList.add(StoreItem(6, "RunAbout Sports", "Clothing"))
        storeList.add(StoreItem(7, "Total Motion", "Gym"))
        storeList.add(StoreItem(8, "CharMarie Salon", "Hair Salon"))
        storeList.add(StoreItem(9,"Loft", "Clothing"))
        storeList.add(StoreItem(10, "Ashley Homestore", "Clothing"))


        return storeList
    }

    private inner class MyAdapter (private val storeList: List<StoreItem>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
        inner class MyViewHolder (val view: View) : RecyclerView.ViewHolder(view)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
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
        }

    }
}
