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
                val rutaFichero = comprobarRuta(ruta)
                if (rutaFichero == null) {
                    calculadora.obtenerRutaDir(ruta)
                    calculadora.iniciar()
                } else {
                    calculadora.obtenerRutaFichero(rutaFichero)
                    calculadora.iniciar()
                }
            }

            1 -> {
                val ruta = args[0]
                val rutaFichero = comprobarRuta(ruta)
                if (rutaFichero == null) {
                    calculadora.obtenerRutaDir(ruta)
                    calculadora.iniciar()
                } else {
                    calculadora.obtenerRutaFichero(rutaFichero)
                    calculadora.iniciar()
                }
            }

            4 -> {
                val ruta = args[0]
                val num1 = args[1].toDoubleOrNull() ?: throw IllegalArgumentException("Valor no válido")
                val num2 = args[3].toDoubleOrNull() ?: throw IllegalArgumentException("Valor no válido")
                val operador = Operadores.getOperador(args[2].trim()[0]) ?: throw IllegalArgumentException("Operador no válido")


                if (fich.existeFichero(ruta)) {
                    calculadora.obtenerRutaFichero(ruta)
                } else if (fich.existeDirectorio(ruta)){
                    calculadora.obtenerRutaDir(ruta)
                } else throw IllegalArgumentException("Ruta no existente")

                calculadora.iniciar(num1, num2, operador)
            }
            else -> {
                throw IllegalArgumentException("Número de argumentos no válido.")
            }
        }
    }


    private fun comprobarRuta(ruta: String): String?{
        if (!fich.existeDirectorio(ruta)) {
            fich.crearDirectorio(ruta)
            ui.mostrar("Ruta $ruta creada")
        } else {
            if (!fich.tieneFicheros(ruta)) {
                ui.mostrar("No existen ficheros de Log")
            } else {
                val ultimo = fich.obtenerRutaUltimoFicheroModificado(ruta)
                val lineas = fich.leerFichero(ultimo)
                ui.mostrar("Lineas del último log realizado\n--------------------------------------")
                ui.mostrarLista(lineas)

                return ultimo
            }
        }
        return null
    }
}