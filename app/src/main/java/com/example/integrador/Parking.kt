package com.example.integrador


data class Parking(val vehicles: MutableSet<Vehicle>) {

    private val cupos = 20
    var ganancias = Pair(0, 0)

    data class Parkeable(val vehicle: Vehicle) {
        // en teoria esta clase deberia usar el  checkin y check out del vehivulo

        private val dosHoras = 120//
        private val tiempoFicticio = 136 //min
        private val pay = 0

        fun calculateFee(type: VehicleType, parkedTime: Int): Int {

            val tarifaBase = type.tarifa
            val hasDiscountCard = vehicle.discountCard

            // calcular tiempo de salida si excede las 2 horas
            val excedeTiempoBase = tiempoFicticio < dosHoras
            val estadiaEnMin = ((tiempoFicticio - dosHoras))

            //calcular bloques
            val bloques = if ((estadiaEnMin % 15) != 0) estadiaEnMin / 15 + 1 else estadiaEnMin / 15
            //println("tiempo de estadia : $tiempoFicticio")
            val pay = bloques * 5 + tarifaBase

            return when (vehicle.discountCard) {

                //no tiene descuento
                null -> if (excedeTiempoBase) tarifaBase else pay
                // tiene decuento
                else -> if (excedeTiempoBase) {
                    (tarifaBase.calcularDescuento(15))
                } else pay.calcularDescuento(15)
            }

        }

        private fun Int.calcularDescuento(discount: Int): Int =
            if (discount == 0) 0 else this - (this * discount / 100)

        fun chekOutVehicle(plate: String) {

            val payment1 = this.calculateFee(vehicle.type, vehicle.parkedTime.toInt())
            println("Check-out $plate:")
            fun onSuccess(payment: Int = payment1) =
                println("Your fee is $payment. Come back soon.")

            fun onError() = println("Sorry, the check-out failed")

            return when (!plate.isNullOrBlank()) { // se puede cambiar por otra validacion importante
                true -> onSuccess()
                else -> onError()
            }
        }

        // acumular ganacias
        fun totalProfit(cantidad: Int, valor: Int, ganancias: Pair<Int, Int>): Pair<Int, Int> {

            val totalPair = Pair(ganancias.first + cantidad, ganancias.second + valor)

            return totalPair
        }
    }

    //agrega vehiculos
    fun addVehicle(vehicle: Vehicle): Boolean {

        val hayCupos = this.vehicles.size < cupos
        if (hayCupos) this.vehicles.add(vehicle)

//        else Parkeable(vehicle).onError()
        return hayCupos
    }

    //creo que no es necesaria
    fun checkIn(added: Boolean) =
        if (added) println("Welcome to AlterParking!") else println("Sorry, the check-in failed")

    //lista los vehiculos del estacionamiento
    fun listVehicles() = vehicles.forEach { println(it.plate) }


    fun remove(vehicle: Vehicle) {

        val retiro = Parkeable(vehicle)

        vehicles.remove(vehicle)
        val pago = retiro.calculateFee(vehicle.type, vehicle.parkedTime.toInt())
        this.ganancias = retiro.totalProfit(1, pago, ganancias)
        retiro.chekOutVehicle(vehicle.plate)


    }


}

