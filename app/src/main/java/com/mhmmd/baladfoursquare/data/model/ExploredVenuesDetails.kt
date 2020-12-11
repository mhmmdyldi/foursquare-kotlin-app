package com.mhmmd.baladfoursquare.data.model

import com.google.gson.annotations.SerializedName

data class ExploredVenuesDetails (
    @SerializedName("response")
    val exploredVenuesResponse : ExploredVenuesResponse?
)
{
    data class ExploredVenuesResponse (
        @SerializedName("groups")
        val groups : List<ExploredVenuesGroups>?
    )
    {
      data class ExploredVenuesGroups (
          @SerializedName("items")
          val venuesItems : List<ExploredVenuesItems>?
      )
      {
        data class ExploredVenuesItems(
            @SerializedName("venue")
            val venue : VenueDetails?
        )
      }
    }
}