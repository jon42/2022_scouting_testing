package com.example.test1

import android.view.View
import kotlinx.serialization.Serializable

@Serializable
data class Data (var Team: Int, var Pose: String, var Match: Int) {

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
    fun calcClimbPoints(): Int{
        when(climb){
            "" -> return 0
            "Low" -> return 4
            "Mid" -> return 6
            "High" -> return 10
        }
        return 15
    }
    private fun totalPoints(): Int{
        var taxiVal = if(taxi) 2 else 0
        return (autoHighGoal * 4 + autoLowGoal * 2 + taxiVal + teleHighGoal * 2 + teleLowGoal - foul + calcClimbPoints())
    }
    fun finStr(): String{
        return ("Team: " + Team + "\nMatch Number: " + Match +  "\n" + win + "\n\nTotal Points: " + totalPoints() + "\n\nAuto: " + (autoLowGoal * 2 + autoHighGoal * 4)+ "pts"
                + "\nLow Goal: " + autoLowGoal + "   High Goal: " + autoHighGoal + "\nMissed: " + missedShotsAuto + "  taxi: " + taxi +"  Steals: " + steal
                + "\n\nTeleOp: " + (teleHighGoal * 2 + teleLowGoal + calcClimbPoints()) + "pts" + "\nLowGoal: " + teleLowGoal + "   High Goal:" + teleHighGoal
                + "\nMissed: " + missedShotsTele + "\nClimb: " + climb +
                 "\nFoul: " + foul + "\nNotes: " + notes)
    }
    fun addNote(str: String){
        notes = str
    }
}
