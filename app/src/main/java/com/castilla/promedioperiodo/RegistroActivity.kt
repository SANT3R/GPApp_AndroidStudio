package com.castilla.promedioperiodo

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.promedioperiodo.Estudiante
import com.example.promedioperiodo.Operaciones

class RegistroActivity : AppCompatActivity() {

    //Se instancian los componentes
    var campoDocumento:EditText?=null
    var campoNombre:EditText?=null
    var campoEdad:EditText?=null
    var campoTelefono:EditText?=null
    var campoDireccion:EditText?=null

    var campoMateria1:EditText?=null
    var campoMateria2:EditText?=null
    var campoMateria3:EditText?=null
    var campoMateria4:EditText?=null
    var campoMateria5:EditText?=null
    var campoNota1:EditText?=null
    var campoNota2:EditText?=null
    var campoNota3:EditText?=null
    var campoNota4:EditText?=null
    var campoNota5:EditText?=null

    var operaciones: Operaciones?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        iniciarComponentes()
    }

    private fun iniciarComponentes() {
        var bundle:Bundle?=this.intent.extras
        operaciones= bundle?.getSerializable("operaciones") as Operaciones?
        campoDocumento=findViewById(R.id.editTextDocumento)
        campoNombre=findViewById(R.id.editTextNombre)
        campoEdad=findViewById(R.id.editTextEdad)
        campoTelefono=findViewById(R.id.editTextTelefono)
        campoDireccion=findViewById(R.id.editTextDireccion)

        campoMateria1=findViewById(R.id.editTextMateria1)
        campoMateria2=findViewById(R.id.editTextMateria2)
        campoMateria3=findViewById(R.id.editTextMateria3)
        campoMateria4=findViewById(R.id.editTextMateria4)
        campoMateria5=findViewById(R.id.editTextMateria5)

        campoNota1=findViewById(R.id.editTextNota1)
        campoNota2=findViewById(R.id.editTextNota2)
        campoNota3=findViewById(R.id.editTextNota3)
        campoNota4=findViewById(R.id.editTextNota4)
        campoNota5=findViewById(R.id.editTextNota5)

        var btnRegistro:Button=findViewById(R.id.btnRegistrar)
        btnRegistro.setOnClickListener { validarCampos() }
    }

    private fun validarCampos(){
        var nota1: Double = campoNota1?.text.toString().toDouble()
        var nota2: Double = campoNota2?.text.toString().toDouble()
        var nota3: Double = campoNota3?.text.toString().toDouble()
        var nota4: Double = campoNota4?.text.toString().toDouble()
        var nota5: Double = campoNota5?.text.toString().toDouble()
        var retorno= true

        if(nota1 > 5 || nota1<0){
            campoNota1?.error= "Se deben colocar valores entre 0 y 5"
            retorno=false
        }
        if(nota2 > 5 || nota2<0){
            campoNota2?.error= "Se deben colocar valores entre 0 y 5"
            retorno=false
        }
        if(nota3 > 5 || nota3<0){
            campoNota3?.error= "Se deben colocar valores entre 0 y 5"
            retorno=false
        }
        if(nota4 > 5 || nota4<0){
            campoNota4?.error= "Se deben colocar valores entre 0 y 5"
            retorno=false
        }
        if(nota5 > 5 || nota5<0){
            campoNota5?.error= "Se deben colocar valores entre 0 y 5"
            retorno=false
        }

        if(retorno){
            registrarEstudiante()
        }
    }

    private fun registrarEstudiante() {

        var est:Estudiante= Estudiante()
        est.documento= campoDocumento?.text.toString()
        est.nombre=campoNombre?.text.toString()
        est.edad=campoEdad?.text.toString().toInt()
        est.direccion=campoDireccion?.text.toString()
        est.telefono=campoTelefono?.text.toString()

        est.materia1=campoMateria1?.text.toString()
        est.materia2=campoMateria2?.text.toString()
        est.materia3=campoMateria3?.text.toString()
        est.materia4=campoMateria4?.text.toString()
        est.materia5=campoMateria5?.text.toString()

        est.nota1= campoNota1?.text.toString().toDouble()
        est.nota2= campoNota2?.text.toString().toDouble()
        est.nota3= campoNota3?.text.toString().toDouble()
        est.nota4= campoNota4?.text.toString().toDouble()
        est.nota5= campoNota5?.text.toString().toDouble()

        est.promedio= operaciones!!.calcularPromedio(est)

        operaciones?.registrarEstudiante(est)
        operaciones?.imprimirListaEstudiantes()

        mostrarDialog(est)
    }

    private fun mostrarDialog(est:Estudiante) {
        val map=operaciones?.ganoPerdio(1)
        var estado= map?.get("estado")
        val builder= AlertDialog.Builder(this)
        builder.setTitle("¡¡¡Registro Exitoso!!!")
        builder.setMessage("${campoNombre?.text.toString()} ha sido registrado correctamente y sus datos son: \n" +
                "Documento: ${campoDocumento?.text.toString()}\nNombre: ${campoNombre?.text.toString()}\nEdad: ${campoEdad?.text.toString()}\n"+
                "Direccion: ${campoDireccion?.text.toString()}\nTelefono: ${campoTelefono?.text.toString()}\n"+
                "Notas: \n"+
                "${campoMateria1?.text.toString()}: ${campoNota1?.text.toString()}\n${campoMateria2?.text.toString()}: ${campoNota2?.text.toString()}\n"+
                "${campoMateria3?.text.toString()}: ${campoNota3?.text.toString()}\n ${campoMateria4?.text.toString()}: ${campoNota4?.text.toString()}\n"+
                "${campoMateria5?.text.toString()}: ${campoNota5?.text.toString()}\n Su promedio es: ${est.promedio} \n $estado")
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener{ dialog, id ->
            // START THE GAME!
        })

        builder.show()
    }

    private fun devolverResultados(){
        var miIntent: Intent=Intent()
        miIntent.putExtra("resultado", "Registro exitoso")
        var miBundle:Bundle=Bundle()
        miBundle.putSerializable("objetoOperaciones", operaciones)
        miIntent.putExtras(miBundle)
        setResult(RESULT_OK,miIntent)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Toast.makeText(this, "Se cierra el registro Activity", Toast.LENGTH_SHORT).show()
            devolverResultados()
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

    /*override fun onBackPressed() {
        super.onBackPressed()
    }*/
}