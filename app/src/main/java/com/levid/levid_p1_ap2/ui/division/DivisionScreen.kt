@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.levid.levid_p1_ap2.ui.division

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.levid.levid_p1_ap2.data.local.entities.Division

@Composable
fun DivisionScreen(
    viewModel: DivisionViewModel = hiltViewModel()
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(text = "Aprende a dividir")
        Spacer(modifier = Modifier.padding(16.dp))
        val listaDivisiones by viewModel.listaDivisiones.collectAsStateWithLifecycle()
        NombreTextField(viewModel)
        Row {
            Column(
                Modifier.weight(1f)
            ){
                DividendoTextField(viewModel)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Column(
                Modifier.weight(1f)
            ){
                DivisorTextField(viewModel)
            }
        }
        Row {
            Column(
                Modifier.weight(1f)
            ) {
                CocienteTextField(viewModel)
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                ResiduoTextField(viewModel)
            }
        }
        GuardarButton(viewModel)
        MostrarListaDivisiones(listaDivisiones, viewModel)
    }
}
@Composable
fun NombreTextField(viewModel: DivisionViewModel) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "Nombre")},
        value = viewModel.nombre,
        onValueChange = { viewModel.nombre = it }
    )
}

@Composable
fun DividendoTextField(viewModel: DivisionViewModel) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = "Dividendo")},
        value = viewModel.dividendo.toString(),
        onValueChange = { viewModel.dividendo = it.toIntOrNull() ?: 0 }
    )
}
@Composable
fun DivisorTextField(viewModel: DivisionViewModel) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = "Divisor")},
        value = viewModel.divisor.toString(),
        onValueChange = { viewModel.divisor = it.toIntOrNull() ?: 0 }
    )
}
@Composable
fun CocienteTextField(viewModel: DivisionViewModel) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = "Cociente")},
        value = viewModel.cociente.toString(),
        onValueChange = { viewModel.cociente = it.toIntOrNull() ?: 0 }
    )
}

@Composable
fun ResiduoTextField(viewModel: DivisionViewModel) {
    OutlinedTextField(
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text(text = "Residuo")},
        value = viewModel.residuo.toString(),
        onValueChange = { viewModel.residuo = it.toIntOrNull() ?: 0 }
    )
}

@Composable
fun GuardarButton(viewModel: DivisionViewModel) {
    OutlinedButton(
        modifier = Modifier.fillMaxWidth(),
        onClick = { viewModel.guardar()}
    ) {
        Icon(imageVector = Icons.Filled.Add, contentDescription = "Icono de más para guardar.")
        Text("Guardar")
    }
}
@Composable
fun EliminarButton(viewModel: DivisionViewModel, division: Division){
    OutlinedButton(onClick = { viewModel.eliminar(division)}) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "Eliminar icon",
            tint = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
fun MostrarListaDivisiones(listaDivisiones: List<Division>, viewModel: DivisionViewModel) {
    Row {
        Text(text = "Historial de resultados")
        Icon(imageVector = Icons.Filled.Info, contentDescription = "Info icon")
    }
    LazyColumn{
        items(listaDivisiones){division ->
            ContenedorItems(division = division, viewModel)
        }
    }
}
@Composable
fun ContenedorItems(division: Division, viewModel: DivisionViewModel){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(BorderStroke(0.5.dp, color = MaterialTheme.colorScheme.outline))
    ) {
        Text(text = division.nombre)
        Spacer(modifier = Modifier.padding(8.dp))
        Row{
            Text("Dividendo: ${division.dividendo}")
            Spacer(modifier = Modifier.padding(16.dp))
            Text("Divisor: ${division.divisor}")
            Spacer(modifier = Modifier.padding(16.dp))
            Column {
                Row {
                    Text(text = "Eliminar")
                }
                Row{
                    EliminarButton(viewModel = viewModel, division = division)
                }
            }
        }
        Row {
            Text("Cociente: ${division.cociente}")
            Spacer(modifier = Modifier.padding(16.dp))
            Text("Residuo: ${division.residuo}")
        }
    }
}