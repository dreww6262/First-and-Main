@file:Suppress("DEPRECATION")

package com.example.firstandmainwilliamson

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment() : Fragment(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_home, container, false)
        val dealListView = v.findViewById<RecyclerView>(R.id.dealRecyclerView)
        val model: MyViewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        //val model: MyViewModel by viewModels()
        //val deals: List<DealItem> = generateDeals()

        var deals: List<PromoItem> = listOf()
        launch {
            deals = model.getPromos()
            dealListView.adapter = StoreListAdapter(deals)
        }
            /*.observe(viewLifecycleOwner, Observer { it ->
            deals = it
            dealListView.adapter = StoreListAdapter(deals)
        })

             */
        dealListView.adapter = StoreListAdapter(deals)
        dealListView.layoutManager = LinearLayoutManager(activity)

        // Inflate the layout for this fragment
        return v
    }


    inner class StoreListAdapter(var deals: List<PromoItem>) :
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
                //if (view != null) {
                    //empty
                //}
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreViewHolder {
            val v = LayoutInflater.from(parent.context).inflate(R.layout.promo_card_view, parent, false)
            return StoreViewHolder(v)
        }

        override fun getItemCount(): Int {
            return deals.size
        }

        override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
            holder.view.findViewById<TextView>(R.id.promoName).text = deals[position].name
            holder.view.findViewById<TextView>(R.id.storeNameOfPromo).text = deals[position].store
            holder.view.findViewById<TextView>(R.id.expDate).text = deals[position].expiration
            Glide.with(this@HomeFragment)
                .load(deals[position].pictureUrl)
                .apply(RequestOptions().override(192, 192))
                .into(holder.view.findViewById(R.id.promoPoster))
            holder.itemView.setOnClickListener() {
                // interact with item
            }
        }
    }

}
