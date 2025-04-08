package es.iesraprog2425.pruebaes.service

import es.iesraprog2425.pruebaes.ui.IEntradaSalida
import es.iesraprog2425.pruebaes.utils.GestionFicheros

class GestorInicio(private val ui: IEntradaSalida, private val fich: GestionFicheros) {

    fun iniciar(args : Array <String>): String{
        return when (args.size){
            0 -> {
                val ruta = "./log"
                if (!fich.existeDirectorio(ruta)) {
                    fich.crearDirectorio(ruta)
                    ui.mostrar("Ruta $ruta creada")
                } else {
                    if (fich.tieneFicheros(ruta)){
                        (fich.leerFichero.(fich.obtenerUltimoFicheroModificado(ruta)))
                    }
                }

            }
            1-> {
                var ruta: String = ""
                for (arg in args){
                    ruta = arg
                }
                ruta
            }

            4-> {
                var ruta: String = ""
                var num1: String = ""
                var operador: String = ""
                var num2: String = ""

                for (arg in args)
            }
        }
        }
}