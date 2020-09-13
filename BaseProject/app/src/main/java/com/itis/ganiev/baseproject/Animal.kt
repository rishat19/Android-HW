package com.itis.ganiev.baseproject

open class Animal(var name: String?, var age: Int) {

    fun info(): String {
        return "name: $name, age: $age"
    }

}