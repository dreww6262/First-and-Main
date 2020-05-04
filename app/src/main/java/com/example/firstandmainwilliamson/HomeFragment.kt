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
class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        val dealListView = v.findViewById<RecyclerView>(R.id.dealRecyclerView)

        val deals: List<DealItem> = generateDeals()
        dealListView.adapter = StoreListAdapter(deals)
        dealListView.layoutManager = LinearLayoutManager(activity)

        // Inflate the layout for this fragment
        return v
    }

    private fun generateDeals(): List<DealItem> {
        val dealList = mutableListOf<DealItem>()
        dealList.add(DealItem("BOGO Burrito at Chipotle Today Only!", "Chipotle"))
        dealList.add(DealItem("50% OFF SALE AT JOS. A BANK!!", "Joseph A Bank"))
        dealList.add(DealItem("FREE popcorn at Paragon with purchase of movie ticket", "Paragon"))
        dealList.add(DealItem("Kareoke tonight at McClain's", "McClain's"))

        return dealList
    }

    inner class StoreListAdapter(var deals: List<DealItem>) :
        RecyclerView.Adapter<StoreListAdapter.StoreViewHolder>() {
        /*
        private var stores = emptyList<StoreItem>()
        private var storesBackup = emptyList<StoreItem>()

        internal fun setStores(stores: List<StoreItem>) {
            storesBackup = stores
            this.stores = stores
            notifyDataSetChanged()
        }

        fun search(query: String?) {
            stores = stores.filter { it.name.contains(query!!) }
            notifyDataSetChanged()
        }

        fun restore() {
            stores = storesBackup
            notifyDataSetChanged()
        }


         */
        inner class StoreViewHolder(val view: View) : RecyclerView.ViewHolder(view),
            View.OnClickListener {
            override fun onClick(view: View?) {
                if (view != null) {

                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view, parent, false)
            return StoreViewHolder(v)
        }

        override fun getItemCount(): Int {
            return deals.size
        }

        override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
            holder.view.findViewById<TextView>(R.id.storeName).text = deals[position].deal
            holder.view.findViewById<TextView>(R.id.description).text = deals[position].store
            holder.itemView.setOnClickListener() {
                // interact with item
            }
        }
    }

}
