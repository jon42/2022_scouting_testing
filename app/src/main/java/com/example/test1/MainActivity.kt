package com.example.test1

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.end_data.*
import kotlinx.android.synthetic.main.initialize.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    val db = Firebase.firestore


    lateinit var data: Data
    public override fun onStart(){
        super.onStart()

    }
    fun setAuto(view: View){
        setContentView(R.layout.data_input_auto)
    }
    fun setTele(view: View){
        setContentView(R.layout.data_input_tele)
    }
    fun bStart(view: View) {
        setContentView(R.layout.initialize)
    }
    fun bData(view: View){
        var button = findViewById<Button>(view.id)
        if(addTeamNumber.text.toString() == "" || addMatchNumber.text.toString() == "" ) return //checks if there is number in the box if not returns
        setContentView(R.layout.data_input_auto)
        //creates new data with info from buttons
        data = Data(addTeamNumber.text.toString().toInt(),button.text.toString(), addMatchNumber.text.toString().toInt())
    }
    //add values based on what Button was clicked
    fun addC(view: View){
        var level = findViewById<Button>(view.id)
        data.setClimb(level.text.toString())
//        climbTitle.text = "Climb: " + data.getClimb()
    }
    fun setToData(){
        setContentView(R.layout.data_input_auto)

    }
    //auto data collection
    fun addLowAuto(view: View){
        var button = findViewById<Button>(view.id)
        data.addLowAuto()
        button.text = "Low Goal: " + data.getALow()
        data.last(view)
    }
    fun addHighAuto(view: View){
        var button = findViewById<Button>(view.id)
        data.addHighAuto()
        button.text = "High Goal: " + data.getAHigh()
        data.last(view)
    }
    fun addSteal(view: View){
        var button = findViewById<Button>(view.id)
        data.addSteal()
        button.text = "Steal:" + data.getSteal()
    }
    fun taxi(view: View){
        var button = findViewById<Button>(view.id)
        data.taxi()
        button.text = "Taxi: " + data.getTaxi()
        data.last(view)
    }
    fun addMissedShotAuto(view: View){
        var button = findViewById<Button>(view.id)
        data.addMissedShotAuto()
        button.text = "Missed:" + data.getMissedShotAuto()
    }
    //TeleOp Data Collection
    fun addLowTele(view: View){
        var button = findViewById<Button>(view.id)
        data.addLowTele()
        button.text = "Low Goal: " + data.getTLow()
        data.last(view)
    }
    fun addHighTele(view: View){
        var button = findViewById<Button>(view.id)
        data.addHighTele()
        button.text = "High Goal " + data.getTHigh()
        data.last(view)
    }
    fun addFoul(view: View){
        var button = findViewById<Button>(view.id)
        data.addFoul()
        button.text = "Foul " + data.getFoul()
        data.last(view)
    }
    fun addMissedTele(){
        data.addMissedShotTele()
    }
    fun fin(view: View){
        var button = findViewById<Button>(view.id)
        data.win = if(button.text.toString() == "Win")  "Win" else "Lose"
        setContentView(R.layout.end_data)
    DataTXT.text = data.finStr()
        db.collection("data").document(data.Team.toString()).set(data, SetOptions.merge())
    }
    fun enterNote(view: View){
        data.addNote(Notes.text.toString())
        DataTXT.text = DataTXT.text.toString() + Notes.text
    }

}