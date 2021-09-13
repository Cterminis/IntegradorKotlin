package com.example.integrador

import java.lang.Math.ceil
import java.util.*


data class Parking(val vehicles: MutableSet<Vehicle>) {

    private val cupos = 20

    data class Parkeable(val vehicle: Vehicle) {
        // en teoria esta clase deberia usar el  checkin y check out del vehivulo

        val dosHoras = 7200L// segundos
        val tiempoFicticio = 1631701107187L

        fun calcularCosto(): Int {
            // calcular tiempo de salida si excede las 2 horas

            println(vehicle.checkInTime.timeInMillis)

            // costo base + $5 por cada 15 min o fraccion 1seg..15min
            val minutos = ((tiempoFicticio - vehicle.checkInTime.timeInMillis) / 60000)
            val bloques = kotlin.math.ceil((minutos / 15).toDouble()).toInt()

            println(minutos)
            println(bloques)
            println("${bloques * 5 + vehicle.type.tarifa} ")

            return when (vehicle.discountCard) {
                //tipo de vehiculo
                // hora de salida --> cuando lo elimino
                //hora de entrada

                null -> vehicle.type.tarifa * vehicle.checkInTime.timeInMillis.toInt()

                //esto es si e tiempo es menor a 2h y tiene decuento
                else -> vehicle.type.tarifa.calcularDescuento(15)
            }

        }

        private fun Int.calcularDescuento(discount: Int): Int =
            if (discount == 0) 0 else this - (this * discount / 100)


        fun chekOutVehicle(plate: String) {

            return when (plate.isBlank()) {
                true -> plate.onSuccess()
                else -> onError()
            }
        }

        // acumular ganacias
        fun totalProfit(cantidad: Int, valor: Int): Pair<Int, Int> {

            val totalPair = Pair(cantidad, valor)

            return totalPair
        }

        fun String.onSuccess() {
            println("plate : $this")
        }

        fun onError() {
            println("error")
        }
    }

    //agrega vehiculos
    fun addVehicle(vehicle: Vehicle): Boolean {

        val hayCupos = this.vehicles.size < cupos
        if (hayCupos) {
            println("Welcome to AlterParking!")
            this.vehicles.add(vehicle)
        } else println("Sorry,the check-in failed")

        return hayCupos
    }

    //creo que no es necesaria
    fun checkIn(added: Boolean) =
        if (added) println("Welcome to AlterParking!") else println("Sorry,the check-in failed")


    //lista los vehiculos del estacionamiento
    fun listVehicles() {
        vehicles.forEach {
            println(it.plate)
        }
    }

    fun remove(element: Vehicle) {

        vehicles.remove(element)
        val pago = Parkeable(element).calcularCosto()

        println("el costo es :$pago  del vehiculos: ${element.plate} ")

    }
}

