package com.example.test1

import kotlinx.serialization.Serializable

@Serializable
data class Data (val Team: Int, val Pose: String,val Match: Int) {

        private var autoHighGoal = 0
        private var autoLowGoal = 0
        private var teleHighGoal = 0
        private var teleLowGoal = 0
        private var climb = ""
        private var foul = 0
        private var notes = ""
        private var lastAction = mutableListOf<String>()

    fun addHighAuto(){
        autoHighGoal++

    }
    fun addLowAuto(){
        autoLowGoal++
    }
    fun addHighTele(){
        teleHighGoal++
    }
    fun addLowTele(){
        teleHighGoal++
    }
    fun addFoul(){
        foul++
    }
    fun setClimb(str: String){
        climb = str
    }
    fun last(Str: String){
        lastAction.add(Str)
        if(lastAction.size > 15){
            lastAction.removeAt(0)
        }
    }
    fun getClimb(): String{
        return climb
    }
    fun undo(){
        if(lastAction.isEmpty()) return
        when(lastAction.last()){
            "auto High" -> autoHighGoal--
            "auto Low" -> autoLowGoal--
            "tele High" -> teleHighGoal--
            "tele Low" -> teleLowGoal--
            "foul" -> foul--
            "climb" -> climb = ""
            "Note" -> notes =""
            }
        }
    }
