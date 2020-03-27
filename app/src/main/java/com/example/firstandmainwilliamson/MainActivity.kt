package com.example.firstandmainwilliamson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    val adapter = StoreListAdapter()

    override fun onQueryTextChange(newText: String?): Boolean {

        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        adapter.search(query)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.search_store)
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {

                return true
            }

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                adapter.restore()
                return true
            }
        })


        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
//            R.id.restore_list ->{
//               //action to be performed
//            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.storeRecyclerView)


    }

    inner class StoreListAdapter() :
        RecyclerView.Adapter<StoreListAdapter.StoreViewHolder>() {
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
            return stores.size
        }

        override fun onBindViewHolder(holder: StoreViewHolder, position: Int) {
            holder.view.findViewById<TextView>(R.id.title)
            holder.view.findViewById<TextView>(R.id.description)
            holder.itemView.setOnClickListener() {
                // interact with item
            }
        }
    }
}
