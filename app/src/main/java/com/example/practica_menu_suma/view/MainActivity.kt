package com.example.practica_menu_suma.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.practica_menu_suma.model.MainViewModel
import com.example.practica_menu_suma.ui.theme.Practica_menu_sumaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val objeto_view_model: MainViewModel by viewModels()
        // val object_view_model: MainViewModel by viewModels ()
        setContent {

            Column(modifier = Modifier.fillMaxHeight(), horizontalAlignment =Alignment.CenterHorizontally){

                Box(modifier = Modifier

                    .size(width = 400.dp, height = 150.dp), contentAlignment = Alignment.Center) {
                    Text(text = "Cotizador de Autos Nuevos", fontWeight = FontWeight(50))
                }

                Column( Modifier.fillMaxWidth(),) {

                    //ponemos un Texfiel para Estatura
                    Text("Nombre " ,
                        textAlign = TextAlign.Left)
                    var text=remember{  mutableStateOf("")}
                    TextField(value =text.value ,

                        onValueChange ={
                            text.value=it
                            objeto_view_model.CalcularNomCliente(text.value)
                        }
                    )
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Marca",)
                        generarSpinnerMarca(objeto_view_model,"" )


                    }
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {

                        Text(text = "Enganche")
                        generarSpinnerEnganche(objeto_view_model,"")
                    }
                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Column() {
                            Text(text = "Financiamiento (Anual)")


                        }
                        generarSpinnerFinanciamiento(objeto_view_model,"")
                    }
                    Column() {
                        Row()
                        {
                            Button(onClick = {
                                objeto_view_model.CalcularNomCliente(text.value)
                                text.value=""
                            }, modifier = Modifier.background(color = Color.White), shape = CutCornerShape(2.dp)
                            ) {
                                Icon(
                                    Icons.Filled.Refresh,
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(ButtonDefaults.IconSize)
                                )
                                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                                Text("Cotizar")
                            }
                            Spacer(modifier = Modifier.padding(50.dp))
                            //botton Reset
                            Button(onClick = {
                                objeto_view_model.Reset()
                                text.value=""
                            }, modifier = Modifier.background(color = Color.White), shape = CutCornerShape(2.dp)
                            ) {
                                Icon(
                                    Icons.Filled.Refresh,
                                    contentDescription = "Favorite",
                                    modifier = Modifier.size(ButtonDefaults.IconSize)
                                )
                                Spacer(modifier = Modifier.padding(4.dp))
                                Text("Reset")
                            }
                        }
                        //ponemos el boton para calcular
                        Text("Cliente: "+objeto_view_model.nobreCliente.value)
                        Text("Marca: " +objeto_view_model.marcaAuto.value)
                        Text("Enganche: " +objeto_view_model.signoP.value+objeto_view_model.Enganche.value)
                        Text("Monto a financiar:" +objeto_view_model.Financiamiento.value)
                        Text("Financiamiento a: " +objeto_view_model.año.value+objeto_view_model.text+objeto_view_model.Tasaint.value+objeto_view_model.text2)
                        Text("Monto de interes por: " +objeto_view_model.año.value+objeto_view_model.añoString.value+objeto_view_model.MontoPorAños.value)
                        Text("Monto a financiar mas interes="+objeto_view_model.Financiamiento.value+objeto_view_model.SignoMas.value+objeto_view_model.MontoPorAños.value+objeto_view_model.SigIgual.value+objeto_view_model.SignoPeso+objeto_view_model.MontoFinaInt.value)
                        Text("Pagos mensuales por $"+objeto_view_model.PagoMensual.value)
                        Text("Pago Total (Enganche + Financiamiento)= $"+objeto_view_model.Enganche.value+objeto_view_model.SignoMas.value+objeto_view_model.MontoFinaInt.value+objeto_view_model.SigIgual.value+objeto_view_model.PagoTotal.value)

                    }

                }
            }
        }
    }

}

@Composable
fun generarSpinnerMarca(objeto:MainViewModel,texto:String)
{
    var expanded by remember { mutableStateOf(false) }
    val suggestions = arrayOf(
        arrayOf(0,"HondaAcord: $678026.22",678026.22) ,
        arrayOf(1,"Vw Touareg: $879266.15",879266.15),
        arrayOf(2,"BMWX5 $1025366.87",1025366.87),
        arrayOf(3,"Mazda CX7 $988641.02",988641.02),
    )

    Box() {
        Button(onClick = { expanded = !expanded }){

            Text ("Selecciona un Auto")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            suggestions.forEach { label->
                DropdownMenuItem(onClick = {
                    expanded = false
                    //pasamos las funciones calcular sexo
                    objeto.CalcularMarcaAuto(label[1].toString())
                    objeto.preciNor(label[2].toString())
                }) {
                    Text(text = label[1].toString())
                }
            }
        }

    }
}

@Composable
fun generarSpinnerEnganche(objeto:MainViewModel,texto:String)
{
    var expanded by remember { mutableStateOf(false) }
    val suggestions = arrayOf(
        arrayOf(0,"(20%)",20),
        arrayOf(1,"(40%)",40),
        arrayOf(2,"(60%)",60),
    )
    Box() {
        Button(onClick = { expanded = !expanded }){
            Text ("Seleccina Enganche")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {

            suggestions.forEach { label->
                DropdownMenuItem(onClick = {
                    expanded = false
                    //pasamos las funciones calcular sexo
                    objeto.agragarsigporce(label[1].toString())
                    objeto.CalcularEnganche(label[2].toString())
                }) {
                    Text(text = label[1].toString())
                }
            }
        }

    }
}

@Composable
fun generarSpinnerFinanciamiento(objeto:MainViewModel,texto:String)
{
    var expanded by remember { mutableStateOf(false) }
    val suggestions = arrayOf(
        arrayOf(0,1,"1 Años 7.5%"," años=",7.5,"+","="),
        arrayOf(1,2,"2 Años 9.5%"," años=",9.5,"+","="),
        arrayOf(2,3,"3 años 10.3%"," años=",10.3,"+","="),
        arrayOf(3,4,"4 años 12.6%"," años=",12.6,"+","="),
        arrayOf(4,5,"5 años 13.5%"," años=",13.5,"+","="),
    )
    Box() {
        Button(onClick = { expanded = !expanded }){
            Text ("Financiamiento")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            suggestions.forEach { label->
                DropdownMenuItem(onClick = {
                    expanded = false
                    //pasamos las funciones calcular sexo
                    objeto.CalcularFinanciamiento(label[2].toString(),label[3].toString())
                    objeto.GetAño(label[1].toString(),label[4].toString(),label[5].toString(),label[6].toString())
                }) {
                    Text(text = label[2].toString())
                }
            }
        }

    }
}
