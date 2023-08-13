package com.example.ninehoursvideo

import java.util.Locale

class Person {
    val ssn : String = "Mohamed"
        get() {
            return field.lowercase(Locale.ROOT)
        }

    var age : Int = 0
        set(value) {
            field = if (value > 0) value else throw IllegalArgumentException("Age cannot be less than 0")
        }
}