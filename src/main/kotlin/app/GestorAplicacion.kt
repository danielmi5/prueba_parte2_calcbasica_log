package es.iesraprog2425.pruebaes.app

import es.iesraprog2425.pruebaes.model.Operadores
import es.iesraprog2425.pruebaes.service.ServiceLog
import es.iesraprog2425.pruebaes.ui.IEntradaSalida
import es.iesraprog2425.pruebaes.utils.GestionFicheros

class GestorAplicacion(private val ui: IEntradaSalida, private val fich: GestionFicheros, val calculadora: Calculadora) {

    fun ejecutar(args : Array <String>){
        when (args.size) {
            0 -> {
                val ruta = "./log"
                comprobarRuta(ruta)
                calculadora.iniciar()
            }

            1 -> {
                val ruta = args[0]
                comprobarRuta(ruta)
                calculadora.iniciar()
            }

            4 -> {
                val ruta = args[0]
                val num1 = args[1].toDoubleOrNull() ?: return
                val num2 = args[3].toDoubleOrNull() ?: return
                val operador = Operadores.getOperador(args[2].trim()[0]) ?: return

                calculadora.iniciar(num1,num2,operador)
            }
            else -> {
                ui.mostrarError("Número de argumentos no válido.")
            }
        }
    }


    private fun comprobarRuta(ruta: String){
        if (!fich.existeDirectorio(ruta)) {
            fich.crearDirectorio(ruta)
            ui.mostrar("Ruta $ruta creada")
        } else {
            if (!fich.tieneFicheros(ruta)) {
                ui.mostrar("No existen ficheros de Log")
            } else {
                val ultimo = fich.obtenerRutaUltimoFicheroModificado(ruta)
                val lineas = fich.leerFichero(ultimo)
                ui.mostrarLista(lineas)
            }
        }
    }
}