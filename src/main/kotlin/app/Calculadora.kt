package es.iesraprog2425.pruebaes.app

import es.iesraprog2425.pruebaes.model.Operadores
import es.iesraprog2425.pruebaes.ui.IEntradaSalida
import es.iesraprog2425.pruebaes.utils.GestionFicheros
import java.io.File

class Calculadora(private val ui: IEntradaSalida) {

    private fun pedirNumero(msj: String, msjError: String = "Número no válido!"): Double {
        return ui.pedirDouble(msj) ?: throw InfoCalcException(msjError)
    }

    private fun pedirInfo() = Triple(
        pedirNumero("Introduce el primer número: ", "El primer número no es válido!"),
        Operadores.getOperador(ui.pedirInfo("Introduce el operador (+, -, *, /): ").firstOrNull())
            ?: throw InfoCalcException("El operador no es válido!"),
        pedirNumero("Introduce el segundo número: ", "El segundo número no es válido!"))

    private fun realizarCalculo(numero1: Double, operador: Operadores, numero2: Double) =
        when (operador) {
            Operadores.SUMA -> numero1 + numero2
            Operadores.RESTA -> numero1 - numero2
            Operadores.MULTIPLICACION -> numero1 * numero2
            Operadores.DIVISION -> numero1 / numero2
        }

    fun iniciar() {
        do {
            try {
                ui.limpiarPantalla()
                val (numero1, operador, numero2) = pedirInfo()
                val resultado = realizarCalculo(numero1, operador, numero2)
                ui.mostrar("Resultado: %.2f".format(resultado))
            } catch (e: NumberFormatException) {
                ui.mostrarError(e.message ?: "Se ha producido un error!")
            } catch (e: InfoCalcException) {
                ui.mostrarError(e.message ?: "Se ha producido un error!")
            }
        } while (ui.preguntar())
        ui.limpiarPantalla()
    }

    fun iniciar(num1: Double, num2: Double, operador: Operadores) {
        ui.pausar()
        var triple = Triple(num1, operador, num2)

        ui.pausar()
        ui.limpiarPantalla()
        var cont = 0
        do {
            try {
                ui.limpiarPantalla()
                if (cont == 0) cont++ else triple = pedirInfo()
                val resultado = realizarCalculo(triple.first, triple.second, triple.third)
                ui.mostrar("Resultado: %.2f".format(resultado))
            } catch (e: NumberFormatException) {
                ui.mostrarError(e.message ?: "Se ha producido un error!")
            } catch (e: InfoCalcException) {
                ui.mostrarError(e.message ?: "Se ha producido un error!")
            }
        } while (ui.preguntar())
        ui.limpiarPantalla()
    }



}