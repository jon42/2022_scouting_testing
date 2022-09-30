package com.example.test1

import kotlinx.serialization.Serializable

@Serializable
data class Data (var Team: Int, var Pose: String, var Match: Int) {

    //initializes all the data
     var totalPoints = 0
     var autoPoints = 0
    var climbTime = 0.0
    private var autoHighGoal = 0
    private var autoLowGoal = 0
     var telePoints = 0
    private var teleHighGoal = 0
    private var teleLowGoal = 0
    private var climb = ""
    private var foul = 0
    private var notes = ""
    private var prevAction = mutableListOf<String>()
    private var taxi = false
    private var missedShotsAuto = 0
    private var missedShotsTele = 0
    private var steal = 0
    private var win = "Loss"


    //add to values
    fun addHighAuto(){
        autoHighGoal++
        last("aHigh")
    }

    fun addHighTele(){
        teleHighGoal++
        last("tHigh")
    }
    fun addLowAuto(){
        autoLowGoal++
        last("aLow")
    }
    fun addLowTele(){
        teleLowGoal++
        last("tLow")
    }
    fun addFoul(){
        foul++
        last("foul")
    }
    fun taxi(){
        taxi = !taxi
    }
    fun setClimb(str: String){
        climb = str
        last("climb")
    }
    fun addMissedShotAuto(){
        missedShotsAuto++
        last("aMissed")
    }
    fun addMissedShotTele(){
     missedShotsTele++
        last("tMissed")
    }
    fun addSteal(){
        steal++
        last("steal")
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
    fun getNotes(): String{
        return notes
    }
    fun getMissedShotAuto(): Int{
        return missedShotsAuto
    }
    fun getMissedShotTele(): Int{
     return missedShotsTele
    }
    fun getWin(): String{
        return win
    }
    fun setWin(str: String){
        win = str
    }
    fun last(Str: String){
        prevAction.add(Str)
        if(prevAction.size > 15){
            prevAction.removeAt(0)
        }
        println(prevAction)
    }
    fun undo(): Int {
        println(prevAction)
        if(prevAction.isEmpty()) return (R.layout.initialize)
        when(prevAction.removeLast()){
            "aHigh" -> {
                autoHighGoal--
                return R.layout.data_input_auto
            }
            "aLow" -> {
                autoLowGoal--
                return R.layout.data_input_auto
            }
            "tHigh" -> {
                teleHighGoal--
                return R.layout.data_input_tele
            }
            "tLow" -> {
                teleLowGoal--
                return R.layout.data_input_tele
            }
            "climb" -> {
                climb = ""
                return R.layout.data_input_tele
            }
            "foul" -> {
                foul--
            }
            "aMissed" -> {
                missedShotsAuto--
                return R.layout.data_input_auto
            }
            "tMissed" -> {
                missedShotsTele--
                return R.layout.data_input_tele
            }
            "steal" -> {
                steal--
                return R.layout.data_input_auto
            }

        }
        return (0)
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
    private fun autoPoints(): Int{
        var taxiVal = if(taxi) 2 else 0
        return (autoHighGoal * 4 + autoLowGoal * 2 + taxiVal)
    }
    private fun telePoints(): Int{
        return calcClimbPoints() + teleLowGoal + (teleHighGoal * 2)
    }
    fun finStr(): String{
        totalPoints = totalPoints()
        autoPoints = autoPoints()
        telePoints = telePoints()
        return ("Team: $Team \nMatch Number: $Match \n  $win" + "\n\nTotal Points: " + totalPoints() + "\n\nAuto: " + autoPoints+ "pts"
                + "\nLow Goal: " + autoLowGoal + "   High Goal: " + autoHighGoal + "\nMissed: " + missedShotsAuto + "  taxi: " + taxi +"  Steals: " + steal
                + "\n\nTeleOp: " + telePoints + "pts" + "\nLowGoal: " + teleLowGoal + "   High Goal:" + teleHighGoal
                + "\nMissed: " + missedShotsTele + "\nClimb: " + climb +"\nClimb Time: $climbTime"+
                 "\nFoul: " + foul + "\nNotes: " + notes)
    }
    fun addNote(str: String){
        notes += str
    }
}
