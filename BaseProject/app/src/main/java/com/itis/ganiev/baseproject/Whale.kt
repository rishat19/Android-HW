package com.itis.ganiev.baseproject

class Whale(name: String?, age: Int) : Animal(name, age), Swimming {

    override fun swim(): String {
        return "I can swim like a whale"
    }

}