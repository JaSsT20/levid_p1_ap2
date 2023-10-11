package com.levid.levid_p1_ap2.data.repository

import com.levid.levid_p1_ap2.data.local.dao.DivisionDao
import com.levid.levid_p1_ap2.data.local.entities.Division
import javax.inject.Inject

class DivisionRepository @Inject constructor(
    private val divisionDao: DivisionDao
) {
    suspend fun guardar(division: Division) = divisionDao.guardar(division)
    fun obtenerTodo() = divisionDao.obtenerTodo()
}