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

    var mensaje by mutableStateOf("")
    val listaDivisiones: StateFlow<List<Division>> = divisionRepository.obtenerTodo()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    fun guardar(){
        viewModelScope.launch {
            if(camposNoVacios()){
                if(divisorEsValido()){
                    var division = Division(
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
    }
    private fun limpiarCampos(){
        nombre = ""
        dividendo = 0
        divisor = 0
        cociente = 0
        residuo = 0
    }
    fun camposNoVacios(): Boolean{
        return !(nombre.isBlank() || dividendo <= 0 || divisor <= 0)
    }
    private fun divisorEsValido():Boolean{
        return !(divisor > dividendo)
    }

    fun verificarDivision(division: Division):Boolean{
        if(
            (division.dividendo / division.divisor) != division.cociente
            ||
            (division.dividendo % division.divisor) != division.residuo
        ){
            return false
        }
        return (division.dividendo / division.divisor) == division.cociente
    }
}