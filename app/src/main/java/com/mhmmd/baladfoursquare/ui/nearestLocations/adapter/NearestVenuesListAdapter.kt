package com.mhmmd.baladfoursquare.ui.nearestLocations.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mhmmd.baladfoursquare.R
import com.mhmmd.baladfoursquare.data.model.VenueDetails
import kotlinx.android.synthetic.main.venue_item_layout.view.*

class NearestVenuesListAdapter(
    private val venueItems: ArrayList<VenueDetails>
) : RecyclerView.Adapter<NearestVenuesListAdapter.DataViewHolder>() {
    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(venueItem: VenueDetails) {
            itemView.venueName.text = venueItem.name
            itemView.venueAddress.text = venueItem.location.address
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.venue_item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = venueItems.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(venueItems[position])

    fun setItems(list: List<VenueDetails>) {
        venueItems.addAll(list)
    }

}