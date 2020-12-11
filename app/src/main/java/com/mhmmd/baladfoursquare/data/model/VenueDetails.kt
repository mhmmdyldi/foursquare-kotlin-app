package com.mhmmd.baladfoursquare.data.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = VenueDetails.TABLE_NAME)
data class VenueDetails (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    var itemsID : Int?,
    val name : String? = "",
    @Embedded
    val location : VenueLocation
)
{
    companion object {
        const val TABLE_NAME = "venue_details"
        const val ID = "item_id"
    }
}