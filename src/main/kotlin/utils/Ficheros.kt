package es.iesraprog2425.pruebaes.utils

import es.iesraprog2425.pruebaes.ui.IEntradaSalida
import java.io.File

class Ficheros(private val ui: IEntradaSalida) : GestionFicheros {
    override fun aniadirLinea(ruta: String, linea: String) {
        try {
            val file = File(ruta)
            file.appendText("$linea\n")

        } catch (e: Exception) {
            ui.mostrar(e.message.toString())
        }
    }

    override fun existeFichero(ruta: String): Boolean {
        return File(ruta).isFile
    }

    override fun existeDirectorio(ruta: String): Boolean {
        return File(ruta).isDirectory
    }

    override fun crearDirectorio(ruta: String) {
        File(ruta).mkdir()
    }

    override fun crearFichero(ruta: String) {
        File(ruta).createNewFile()
    }

    override fun obtenerUltimoFicheroModificado(ruta: String) : File{
        val directorio = File(ruta)

        var archivoMax = directorio
        var max = 0L
        directorio.listFiles()?.forEach {
            if (max < it.lastModified())
            max = it.lastModified()
            archivoMax = it
        }
        return archivoMax
    }

    override fun tieneFicheros(ruta: String): Boolean {
        return File(ruta).listFiles() != null
    }

    override fun leerFichero(ruta: String): List<String> {
        return File(ruta).readLines()
    }
}