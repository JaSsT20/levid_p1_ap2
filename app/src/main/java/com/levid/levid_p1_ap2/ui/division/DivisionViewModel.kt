package com.levid.levid_p1_ap2.ui.division

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.levid.levid_p1_ap2.data.local.entities.Division
import com.levid.levid_p1_ap2.data.repository.DivisionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DivisionViewModel @Inject constructor(
    private val divisionRepository: DivisionRepository
): ViewModel() {
    var nombre by mutableStateOf("")
    var dividendo by mutableStateOf(0)
    var divisor by mutableStateOf(0)
    var cociente by mutableStateOf(0)
    var residuo by mutableStateOf(0)

    var nombreMsg by mutableStateOf("")
    var dividendoMsg by mutableStateOf("")
    var divisorMsg by mutableStateOf("")
    var cocienteMsg by mutableStateOf("")
    var residuoMsg by mutableStateOf("")
    val listaDivisiones: StateFlow<List<Division>> = divisionRepository.obtenerTodo()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    fun guardar(){
        viewModelScope.launch {
            if(camposNoVaciosYValidos()){
                val division = Division(
                    nombre = nombre,
                    dividendo = dividendo,
                    divisor = divisor,
                    cociente = cociente,
                    residuo = residuo
                )
                divisionRepository.guardar(division)
                limpiarCampos()
            }
        }
    }
    fun eliminar(division: Division){
        viewModelScope.launch {
            divisionRepository.eliminar(division)
        }
    }
    private fun limpiarCampos(){
        nombre = ""
        dividendo = 0
        divisor = 0
        cociente = 0
        residuo = 0
    }
    private fun camposNoVaciosYValidos(): Boolean{
        if(nombre.isBlank()) {
            nombreMsg = "El nombre es obligatorio."
            return false
        }
        else if(dividendo <= 0) {
            dividendoMsg = "El dividendo no puede ser menor o igual a cero."
            return false
        }
        else if(divisor <= 0 || divisor > dividendo){
            divisorMsg = "Divisor inválido."
            return false
        }
        else if(cociente <= 0 || cociente != (dividendo / divisor)){
            cocienteMsg = "Cociente inválido."
            return false
        }
        else if(residuo < 0 || residuo != (dividendo%divisor)){
            residuoMsg = "Residuo inválido."
            return false
        }
        else{
            return true
        }
    }
    private fun divisorEsValido():Boolean{
        return !(divisor > dividendo)
    }
}