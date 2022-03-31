package com.castilla.promedioperiodo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.promedioperiodo.Operaciones

class EstadisticasActivity : AppCompatActivity() {

    var operaciones: Operaciones?=null

    var campoProcesados:TextView?=null
    var campoGanan:TextView?=null
    var campoPierden:TextView?=null
    var campoRecuperan:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estadisticas)

        iniciarComponentes()
    }

    private fun iniciarComponentes() {
        var bundle:Bundle?=this.intent.extras
        operaciones= bundle?.getSerializable("operaciones") as Operaciones?

        campoProcesados=findViewById(R.id.campoEstProcesados)
        campoGanan=findViewById(R.id.campoEstGanan)
        campoPierden=findViewById(R.id.campoEstPierden)
        campoRecuperan=findViewById(R.id.campoEstRecuperar)

        llenarCampos()
    }

    private fun llenarCampos() {
        val map=operaciones?.ganoPerdio(2)
        var cantidadEstudiantes=operaciones?.listaEstudiantes?.size
        campoProcesados?.text=cantidadEstudiantes.toString()

        var cantGanan= map?.get("ganan")
        var cantPierden= map?.get("pierden")
        var cantRecuperan= map?.get("recuperan")
        campoGanan?.text=cantGanan.toString()
        campoRecuperan?.text=cantRecuperan.toString()
        campoPierden?.text=cantPierden.toString()
    }
}