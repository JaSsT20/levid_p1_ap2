package com.levid.levid_p1_ap2.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Divisiones")
data class Division(
    @PrimaryKey
    val divisionId: Int? = null,
    var nombre: String = "",
    var dividendo : Int = 0,
    var divisor : Int = 0,
    var cociente : Int = 0,
    var residuo : Int = 0,
)
