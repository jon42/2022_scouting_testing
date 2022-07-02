package com.example.test1

import kotlinx.serialization.Serializable

@Serializable
class Data (Team: Int, Pose: String, Match: Int) {


    private var autoHighGoal = 0
    private var autoLowGoal = 0
    private var teleHighGoal = 0
    private var teleLowGoal = 0
    private var climb = 0
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
    fun lowC(){
        climb = 1
    }
    fun midC(){
        climb = 2
    }
    fun highC() {
        climb = 3
    }
    fun travC(){
        climb = 4
    }
    fun last(Str: String){
        lastAction.add(Str)
        if(lastAction.size > 15){
            lastAction.removeAt(0)
        }
    }
    fun undo(){
        if(lastAction.isEmpty()) return
        when(lastAction.last()){
            "auto High" -> autoHighGoal--
            "auto Low" -> autoLowGoal--
            "tele High" -> teleHighGoal--
            "tele Low" -> teleLowGoal--
            "foul" -> foul--
            "climb" -> climb--
            "Note" -> notes =""
            }
        }
    }
