package es.iesraprog2425.pruebaes

import es.iesraprog2425.pruebaes.app.Calculadora
import es.iesraprog2425.pruebaes.app.GestorAplicacion
import es.iesraprog2425.pruebaes.service.GestorLogs
import es.iesraprog2425.pruebaes.ui.Consola
import es.iesraprog2425.pruebaes.utils.Ficheros

/*
fun main() {
    val scanner = Scanner(System.`in`)

    println("Introduce el primer número:")
    val numero1 = scanner.nextDouble()
    println("Introduce el operador (+, -, *, /):")
    val operador = scanner.next()[0]
    println("Introduce el segundo número:")
    val numero2 = scanner.nextDouble()

    val resultado = when (operador) {
        '+' -> numero1 + numero2
        '-' -> numero1 - numero2
        '*' -> numero1 * numero2
        '/' -> numero1 / numero2
        else -> "Operador no válido"
    }

    println("Resultado: $resultado")
}
*/

fun main(args: Array <String>) {
    val consola = Consola()
    val fich = Ficheros(consola)
    val gestorLog = GestorLogs(fich)
    val calc = Calculadora(consola, gestorLog)
    val valores = arrayOf<String>()
    val gestor = GestorAplicacion(consola, fich, calc)

    gestor.ejecutar(valores)

}


/*
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)

    val numLineas = scanner.nextInt()
    scanner.nextLine() // Limpia el salto de línea pendiente

    var resultado = 1

    for (i in 1..numLineas) {
        var suma = 0
        while (scanner.hasNextInt()) {
            suma += scanner.nextInt()
        }
        resultado *= suma
        if (scanner.hasNextLine()) scanner.nextLine() // pasar a la siguiente línea
    }

    println(resultado)
}
*/