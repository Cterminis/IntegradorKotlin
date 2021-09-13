package com.example.integrador

import java.util.*

fun main() {

    val car = Vehicle("KK1111j", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_001")
    val car4 = Vehicle("DD1111k", VehicleType.CAR, Calendar.getInstance())
    val parking = Parking(mutableSetOf(car))

    println("verificar si ingreso el vehiculo")
    println(parking.vehicles.contains(car))
//    println(parking.vehicles.contains(moto))
//    println(parking.vehicles.contains(bus))
//    println(parking.vehicles.contains(minibus))

//    val isCar2Inserted = parking.vehicles.add(car2)
//    println(isCar2Inserted)
//    println(parking.vehicle.remove(moto))

//    parking = Parking(completeParking())
    println("---------------------")
    println("ingresando 20 vehiculos")
    completeParking().forEach { parking.addVehicle(it) }
    println("---------------------")
    println("check out ${car.plate}")
    parking.remove(car)
    println("---------------------")
    println(car.parkedTime)
    println("---------------------")
    parking.remove(car4)
    println("---------------------")
    println("Cantidad de vehiculos retirados ${parking.ganancias.first}")
    println("Ganancias acumulada:$ ${parking.ganancias.second}")
    println("---------------------")
    println("imprimiendo lista")
    println("---------------------")
    parking.listVehicles()


}

fun completeParking(): MutableSet<Vehicle> {
    val car = Vehicle("KK1111Pj", VehicleType.CAR, Calendar.getInstance(), "DISCOUNT_CARD_001")
    val car2 = Vehicle("ll1111p", VehicleType.CAR, Calendar.getInstance())
    val car3 = Vehicle("mm1111k", VehicleType.CAR, Calendar.getInstance())
    val car4 = Vehicle("DD1111k", VehicleType.CAR, Calendar.getInstance())
    val car5 = Vehicle("PP1111p", VehicleType.CAR, Calendar.getInstance())
    val car6 = Vehicle("RR1111i", VehicleType.CAR, Calendar.getInstance())
    val car7 = Vehicle("GS1111jt", VehicleType.CAR, Calendar.getInstance())
    val car8 = Vehicle("SD1111jq", VehicleType.CAR, Calendar.getInstance())
    val car9 = Vehicle("EE1111je", VehicleType.CAR, Calendar.getInstance())
    val car10 = Vehicle("EE1111jr", VehicleType.CAR, Calendar.getInstance())
    val moto1 = Vehicle("Wp2212Jt", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val moto2 = Vehicle("PL2212Jy", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val moto3 = Vehicle("LM2212Ju", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val moto4 = Vehicle("NL2212Ji", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val moto5 = Vehicle("QW2212Jo", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val moto6 = Vehicle("PL2212Jo", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val moto7 = Vehicle("ZA2212Jq", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val moto8 = Vehicle("PL9212Jx", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val moto9 = Vehicle("MM2212Jb", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val moto19 = Vehicle("MM2212ob", VehicleType.MOTORCYCLE, Calendar.getInstance())
    val mot10 = Vehicle("122212Jm", VehicleType.MOTORCYCLE, Calendar.getInstance())
    return mutableSetOf(
        car, car2, car3, car4, car5, car6, car7, car8, car9, car10,
        mot10, moto1, moto2, moto3, moto4, moto5, moto6, moto7, moto8, moto9
    )


}
