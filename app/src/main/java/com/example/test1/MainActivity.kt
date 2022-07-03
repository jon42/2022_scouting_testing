package com.example.test1

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.data_input.*
import kotlinx.android.synthetic.main.end_data.*
import kotlinx.android.synthetic.main.initialize.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        mAuth = FirebaseAuth.getInstance()
    }
    lateinit var data: Data
    public override fun onStart(){
        super.onStart()
//        var currentUser = mAuth.currentUser



    }

    fun bStart(view: View) {
        setContentView(R.layout.initialize)
    }
    fun bData(view: View){
        var button = findViewById<Button>(view.id)
        if(addTeamNumber.text.toString() == "" || addMatchNumber.text.toString() == "" ) return //checks if there is number in the box if not returns
        setContentView(R.layout.data_input)
        //creates new data with info from buttons
        data = Data(addTeamNumber.text.toString().toInt(),button.text.toString(), addMatchNumber.text.toString().toInt())
        print(data.Match.toString() + " " + data.Team + " " + data.Pose)
    }
    //add values based on what Button was clicked
    fun addC(view: View){
        var level = findViewById<Button>(view.id)
        data.setClimb(level.text.toString())
        climbTitle.text = "Climb: " + data.getClimb()
    }
    fun setToData(){
        setContentView(R.layout.data_input)

    }
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

    fun taxi(view: View){
        data.taxi()
        setTaxi.text = "Taxi: " + data.getTaxi()
        data.last(view)
    }
    fun addFoul(view: View){
        var button = findViewById<Button>(view.id)
        data.addFoul()
        button.text = "Foul " + data.getFoul()
        data.last(view)
    }
    fun fin(view: View){
        var button = findViewById<Button>(view.id)
        data.win = if(button.text.toString().equals("Win"))  "Win" else "Win"
        setContentView(R.layout.end_data)
    DataTXT.text = data.finStr()
    }
    fun enterNote(view: View){
        data.addNote(Notes.text.toString())
        DataTXT.text = DataTXT.text.toString() + Notes.text
    }

}