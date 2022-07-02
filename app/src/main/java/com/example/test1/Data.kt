package com.example.test1

import android.view.View
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
    private var lastAction = mutableListOf<View>()
    private var taxi = false

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
        teleLowGoal++
    }
    fun addFoul(){
        foul++
    }
    fun taxi(){
        taxi = !taxi
    }
    fun setClimb(str: String){
        climb = str
    }

    fun getClimb(): String{
        return climb
    }
    fun getALow(): Int{
        return autoLowGoal
    }
    fun getAHigh(): Int{
        return autoHighGoal
    }
    fun getTLow(): Int{
        return teleLowGoal
    }
    fun getTHigh(): Int{
        return teleHighGoal
    }
    fun getFoul(): Int{
        return foul
    }
    fun getTaxi(): Boolean{
        return taxi
    }
    fun last(Str: View){
        lastAction.add(Str)
        if(lastAction.size > 15){
            lastAction.removeAt(0)
        }
    }
    fun undo(){
        if(lastAction.isEmpty()) return
        when(lastAction.last()){

            }
        }
    }
