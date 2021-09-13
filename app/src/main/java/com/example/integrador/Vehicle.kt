package com.example.integrador

import java.util.*

class Vehicle(val plate: String,
              val type: VehicleType,
              val checkInTime: Calendar,
              val discountCard: String? = null
) {
    val MINUTES_IN_MILISECONDS = 60000
    val parkedTime: Long
    get() =
        (Calendar.getInstance().timeInMillis - checkInTime.timeInMillis) / MINUTES_IN_MILISECONDS

    override fun equals(other: Any?): Boolean {
        if (other is Vehicle) {
            return this.plate == other.plate
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return this.plate.hashCode()
    }


}