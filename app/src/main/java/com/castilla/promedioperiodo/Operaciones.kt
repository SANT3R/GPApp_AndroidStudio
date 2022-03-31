package com.example.promedioperiodo

import android.util.Log
import java.io.Serializable
import kotlin.math.log

class Operaciones : Serializable {
    var listaEstudiantes: ArrayList<Estudiante> =arrayListOf<Estudiante>()

    fun registrarEstudiante(estudiante: Estudiante){
        listaEstudiantes.add(estudiante)
    }

    fun imprimirListaEstudiantes(){
        for (est in listaEstudiantes){
            println(est)
        }
    }

    fun calcularPromedio(est: Estudiante):Double{
        var prom=(est.nota1+est.nota2+est.nota3+est.nota4+est.nota5)/5
        return prom
    }

    fun ganoPerdio(num: Int): MutableMap<String, String> {
        val resultado= mutableMapOf<String, String>()
        var cantGanan:Int=0
        var cantPierden:Int=0
        var cantRecuperan:Int=0
        var estado=""
        for (est in listaEstudiantes){
            if(est.promedio>=3.5){
                cantGanan++
                estado="!!!FELICIDADES GANO EL PERIODO!!!"
            }else{
                cantPierden++
                estado="!!!LO SENTIMOS, PERDIO EL PERIODO!!!"
                if(est.promedio>=2.5){
                    cantRecuperan++
                    estado="!!!LO SENTIMOS, PERDIO EL PERIODO!!!... PERO LO PUEDE RECUPERAR!!"
                }
            }
        }
        when(num){
            1->{
                resultado.put("estado", estado)
            }
            2->{
                resultado.put("ganan", cantGanan.toString())
                resultado.put("pierden", cantPierden.toString())
                resultado.put("recuperan", cantRecuperan.toString())
            }
        }
        return resultado
    }
}