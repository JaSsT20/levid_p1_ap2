package com.levid.levid_p1_ap2.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.levid.levid_p1_ap2.data.local.entities.Division
import kotlinx.coroutines.flow.Flow

@Dao
interface DivisionDao {
    @Upsert
    suspend fun guardar(division: Division)

    @Query("SELECT * FROM Divisiones")
    fun obtenerTodo() : Flow<List<Division>>

    @Delete
    suspend fun eliminar(division: Division)
}