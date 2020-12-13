package com.mhmmd.baladfoursquare.data.local.db.dao

import androidx.room.*
import com.mhmmd.baladfoursquare.data.model.VenueDetails
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface VenueDetailsDoa {
    @Query("SELECT * FROM ${VenueDetails.TABLE_NAME}")
    fun loadAll(): Single<List<VenueDetails>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg venue: VenueDetails): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(venuesList: List<VenueDetails>): Completable

    @Delete
    fun delete(venue: VenueDetails): Completable

    @Update
    fun update(vararg venue: VenueDetails)

    @Query("DELETE FROM ${VenueDetails.TABLE_NAME}")
    fun deleteAll(): Completable
}