package com.example.test1

import android.view.View
import kotlinx.serialization.Serializable

@Serializable
data class Data (val Team: Int, val Pose: String,val Match: Int) {
    fun constructor(){

    }
    //initializes all the data
    private var autoHighGoal = 0
    private var autoLowGoal = 0
    private var teleHighGoal = 0
    private var teleLowGoal = 0
    private var climb = ""
    private var foul = 0
    private var notes = ""
    private var lastAction = mutableListOf<View>()
    private var taxi = false
    private var missedShotsAuto = 0
    private var missedShotsTele = 0
    private var steal = 0
    var win = "Loss"

    //add to values
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
    fun addMissedShotAuto(){
        missedShotsAuto++
    }
    fun addMissedShotTele(){
     missedShotsTele++
    }
    fun addSteal(){
        steal++
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
    fun getSteal(): Int{
        return steal
    }
    fun getMissedShotAuto(): Int{
        return missedShotsAuto
    }
    fun getMissedShotTele(): Int{
     return missedShotsTele
    }
    fun last(Str: View){
        lastAction.add(Str)
        if(lastAction.size > 15){
            lastAction.removeAt(0)
        }
    }
    fun undo(){
        if(lastAction.isEmpty()) return
        when(lastAction.last().id){

            }
        }
    private fun totalPoints(): Int{
        var taxiVal = if(taxi) 2 else 0
        var climbVal = 15
        when(climb){
            "" -> climbVal = 0
            "Low" -> climbVal = 4
            "Mid" -> climbVal = 6
            "High" -> climbVal = 10
        }
        return (autoHighGoal * 4 + autoLowGoal * 2 + taxiVal + teleHighGoal * 2 + teleLowGoal - foul + climbVal)
    }
    fun finStr(): String{
        return (Team.toString() + " Match Number: " + Match +  "\n" + win + "\n\nTotal Points: " + totalPoints() + "\n\nAuto:\nLow Goal: " + autoLowGoal +
                "   High Goal: " + autoHighGoal + "  taxi: " + taxi + "\n\nTeleop:" + "\nLowGoal: " + teleLowGoal + "   High Goal:" + teleHighGoal + "\nClimb: " + climb +
                 "\nFoul: " + foul + "\nNotes: " + notes)
    }
    fun addNote(str: String){
        notes = str
    }
}
