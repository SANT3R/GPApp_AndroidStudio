package com.castilla.promedioperiodo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.example.promedioperiodo.Operaciones

class MainActivity : AppCompatActivity() {

    var operaciones: Operaciones?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        operaciones= Operaciones()
        iniciarComponentess()
    }

    private val response=registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { valor ->
        if (valor.resultCode=== RESULT_OK){
            val resp=valor?.data?.extras?.get("resultado") as String
            val resp2=valor?.data?.getStringExtra("resultado")
            println("valor respuesta=$resp y la resps2=$resp2")
            operaciones= valor?.data?.extras?.get("objetoOperaciones") as Operaciones?
            operaciones?.imprimirListaEstudiantes()
        }
    }

    private fun iniciarComponentess() {
        val botonRegistro: Button=findViewById(R.id.btnRegistrarse)
        val botonEstadisticas:Button=findViewById(R.id.btnEstadisticas)
        val botonAyuda:Button=findViewById(R.id.btnAyuda)

        botonRegistro.setOnClickListener { onClick(1) }
        botonEstadisticas.setOnClickListener { onClick(2) }
        botonAyuda.setOnClickListener { onClick(3) }
    }

    private fun onClick(boton: Int) {
        when(boton){
            1->{
                var miIntent= Intent(this, RegistroActivity::class.java)
                var miBundle:Bundle= Bundle()
                miBundle.putSerializable("operaciones",operaciones)
                miBundle.putString("Nombre", "Pepe")
                miIntent.putExtras(miBundle)
                miIntent.putExtra("Objeto",operaciones)
                response.launch(miIntent)
            }
            2->{
                var miIntent:Intent=Intent(this, EstadisticasActivity::class.java)
                var miBundle:Bundle= Bundle()
                miBundle.putSerializable("operaciones", operaciones)
                miIntent.putExtras(miBundle)
                miIntent.putExtra("objeto", operaciones)
                response.launch(miIntent)
//                startActivity(Intent(this,AyudaActivity::class.java))
            }
            3->startActivity(Intent(this,AyudaActivity::class.java))
        }
    }
}