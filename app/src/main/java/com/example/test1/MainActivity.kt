package com.example.test1

import android.app.Activity
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.data_input_tele.*
import kotlinx.android.synthetic.main.end_data.*
import kotlinx.android.synthetic.main.initialize.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    val db = Firebase.firestore
    var init = R.layout.initialize

    private var dataStr = ""
    lateinit var data: Data
    private var prevActions = mutableListOf<String>()
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
        setContentView(init)
    }
    fun bData(view: View){
        var teamNumber: EditText = findViewById(R.id.addTeamNumber)
        var matchNumber: EditText = findViewById(R.id.addMatchNumber)
        var button = findViewById<Button>(view.id)
        if(teamNumber.text.toString() == "" || matchNumber.text.toString() == "" ) return //checks if there is number in the box if not returns
        setContentView(R.layout.data_input_auto)
        //creates new data with info from buttons
        println(teamNumber.text)
        data = Data(teamNumber.text.toString().toInt(),button.text.toString(), matchNumber.text.toString().toInt())
        println(data.Team)
    }
    //add values based on what Button was clicked
    fun addC(view: View){
        var level = findViewById<Button>(view.id)
        data.setClimb(level.text.toString())
        ClimbTitle.text = "Climb: " + data.getClimb()
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
        button.text = "High Goal: " + data.getTHigh()
        data.last(view)
    }
    fun addFoul(view: View){
        var button = findViewById<Button>(view.id)
        data.addFoul()
        button.text = "Foul " + data.getFoul()
        data.last(view)
    }
    fun addMissedTele(view: View){
        var button = findViewById<Button>(view.id)
        data.addMissedShotTele()
        button.text = "Missed: " + data.getMissedShotTele()
    }
    fun fin(view: View){
        var button = findViewById<Button>(view.id)
        data.win = if(button.text.toString() == "Win")  "Win" else "Lose"
        dataStr = data.finStr()
//        DataTXT.setText(dataStr)
        setContentView(R.layout.end_data)
        var dTXT: TextView  = findViewById(R.id.DataTXT)
        dTXT.text = dataStr
    }
    fun enterNote(view: View){
        var dTXT: TextView  = findViewById(R.id.DataTXT)
        data.addNote(Notes.text.toString())
        dTXT.text = dataStr + Notes.text
    }
    fun newData(view: View){
        init = R.layout.initialize
        setContentView(init)
        addTeamNumber.setText("")
        addMatchNumber.setText("")
        db.collection("data").document(data.Team.toString()).set(data, SetOptions.merge())
            .addOnSuccessListener { Log.d(TAG, "Yay! it worked!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

    }
    fun addPrevAction(str: String){
        prevActions.add(str)
        if(prevActions.size > 20) prevActions.removeFirst()
    }
    fun undo(){
        when(prevActions.last()){

        }
    }

}