package com.example.integrador

import java.util.*


data class Parking(val vehicles: MutableSet<Vehicle>) {

    private val cupos = 20
    var ganancias = Pair(0, 0)

    data class Parkeable(val vehicle: Vehicle) {
        // en teoria esta clase deberia usar el  checkin y check out del vehivulo

        val dosHoras = 120L//
        val tiempoFicticio = 135 //min

        fun calcularCosto(): Int {
            // calcular tiempo de salida si excede las 2 horas
//            val excede=vehicle.parkedTime<dosHoras
            val excede=tiempoFicticio<dosHoras

            val estadiaEnMin = ((tiempoFicticio - dosHoras))
//            val estadiaEnMin = ((tiempoFicticio - vehicle.parkedTime))
            //calcular bloques
            val bloques = kotlin.math.ceil((estadiaEnMin/ 15).toDouble()).toInt()

            println("tiempo en minutos $tiempoFicticio")

            val total = bloques * 5 + vehicle.type.tarifa
            // println(" el precio a pagar es: $${bloques * 5 + vehicle.type.tarifa} ")

            return when (vehicle.discountCard) {

                //no tiene descuento
                null -> if (excede) vehicle.type.tarifa else total

                // tiene decuento
                else -> if (excede) vehicle.type.tarifa.calcularDescuento(15) else total.calcularDescuento(15)
            }

        }

        private fun Int.calcularDescuento(discount: Int): Int =
            if (discount == 0) 0 else this - (this * discount / 100)


        fun chekOutVehicle(plate: String) {

            return when (!plate.isNullOrBlank()) {
                true -> plate.onSuccess()
                else -> onError()
            }
        }

        // acumular ganacias
        fun totalProfit(cantidad: Int, valor: Int, ganancias: Pair<Int, Int>): Pair<Int, Int> {

            val totalPair = Pair(ganancias.first+cantidad, ganancias.second+valor)

            return totalPair
        }

        fun String.onSuccess() {
            println("onSucces plate : $this")
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

        Parkeable(element).chekOutVehicle(element.plate)
        vehicles.remove(element)
        val pago = Parkeable(element).calcularCosto()
        this.ganancias = Parkeable(element).totalProfit(1, pago,ganancias)
        println("el precio a pagar es: $$pago  del vehiculos: ${element.plate} ")

    }


}

