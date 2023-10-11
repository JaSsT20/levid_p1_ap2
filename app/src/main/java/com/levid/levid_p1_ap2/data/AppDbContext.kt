package com.levid.levid_p1_ap2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.levid.levid_p1_ap2.data.local.dao.DivisionDao
import com.levid.levid_p1_ap2.data.local.entities.Division

@Database(
    entities = [Division::class],
    version = 1,
    exportSchema = false
)
abstract class AppDbContext : RoomDatabase() {
    abstract fun divisionDao() : DivisionDao
}