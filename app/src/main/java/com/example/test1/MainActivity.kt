package com.example.test1

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.data_input_auto.*
import kotlinx.android.synthetic.main.data_input_tele.*
import kotlinx.android.synthetic.main.end_data.*
import kotlinx.android.synthetic.main.initialize.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.activity_main)
        currentView = R.layout.activity_main
    }

    var init = R.layout.initialize
    val storage = Firebase.database
    private var dataStr = ""
    lateinit var data: Data
    var currentView: Int = 0


    public override fun onStart(){
        super.onStart()
        Firebase.database.setPersistenceEnabled(true)

    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)


    }
    fun setAuto(view: View){
        setView(R.layout.data_input_auto)
    }

    fun bStart(view: View) {
        setView(init)
    }
    fun setTData(){

    }

    fun bData(view: View){
        var teamNumber: EditText = findViewById(R.id.addTeamNumber)
        var matchNumber: EditText = findViewById(R.id.addMatchNumber)
        var button = findViewById<Button>(view.id)
        if(teamNumber.text.toString() == "" || matchNumber.text.toString() == "" ) return //checks if there is number in the box if not returns

        //creates new data with info from buttons
        data = Data(teamNumber.text.toString().toInt(),button.text.toString(), matchNumber.text.toString().toInt())
        setInputAuto()
        println(teamNumber.text)
        println(data.Team)
    }
    //add values based on what Button was clicked
    fun addC(view: View){
        var climbTitle = findViewById<TextView>(ClimbTitle.id)
        var level = findViewById<Button>(view.id)
        data.setClimb(level.text.toString())
        climbTitle.text = "Climb: " + data.getClimb()
        addPrevAction("climb")
    }

    //auto data collection
    fun addLowAuto(view: View){
        var button = findViewById<Button>(view.id)
        data.addLowAuto()
        button.text = "Low Goal:\n" + data.getALow()
        addPrevAction("aLow")
    }
    fun addHighAuto(view: View){
        var button = findViewById<Button>(view.id)
        data.addHighAuto()
        button.text = "High Goal:\n" + data.getAHigh()
        addPrevAction("aHigh")
    }
    fun addSteal(view: View){
        var button = findViewById<Button>(view.id)
        data.addSteal()
        button.text = "Steal:\n" + data.getSteal()
        addPrevAction("steal")
    }
    fun taxi(view: View){
        var button = findViewById<Button>(view.id)
        data.taxi()
        button.text = "Taxi:\n" + data.getTaxi()
    }
    fun addMissedShotAuto(view: View){
        var button = findViewById<Button>(view.id)
        data.addMissedShotAuto()
        button.text = "Missed:\n" + data.getMissedShotAuto()
        addPrevAction("aMissed")
    }
    //TeleOp Data Collection
    fun addLowTele(view: View){
        var button = findViewById<Button>(view.id)
        data.addLowTele()
        button.text = "Low Goal: " + data.getTLow()
        addPrevAction("tLow")
    }
    fun addHighTele(view: View){
        var button = findViewById<Button>(view.id)
        data.addHighTele()
        button.text = "High Goal: " + data.getTHigh()
        addPrevAction("tHigh")
    }
    fun addFoul(view: View){
        var button = findViewById<Button>(view.id)
        data.addFoul()
        button.text = "Foul: " + data.getFoul()
        addPrevAction("foul")
    }
    fun addMissedTele(view: View){
        var button = findViewById<Button>(view.id)
        data.addMissedShotTele()
        button.text = "Missed: " + data.getMissedShotTele()
        addPrevAction("tMissed")
    }
    fun fin(view: View){
        var button = findViewById<Button>(view.id)
        var str = if(button.text.toString() == "Win")  "Win" else "Lose"
        data.setWin(str)
        dataStr = data.finStr()
//        DataTXT.setText(dataStr)
        setView(R.layout.end_data)
        var dTXT: TextView  = findViewById(R.id.DataTXT)
        dTXT.text = dataStr
    }
    fun enterNote(view: View){
        var dTXT: TextView  = findViewById(R.id.DataTXT)
        data.addNote(Notes.text.toString())
        dTXT.text = dataStr + Notes.text
    }
    fun newData(view: View){
        val init = R.layout.initialize
        setView(init)
        addTeamNumber.setText("")
        addMatchNumber.setText("")
        println(data)
        var ref = storage.getReference(data.Team.toString())
        var refRef = ref.child(data.Match.toString())
        refRef.setValue(data)

    }
    fun addPrevAction(str: String){
    }
    @SuppressLint("RestrictedApi")
    fun undo(view: View){
        var tempNum = data.undo()
        when(tempNum){
            0 -> updateScene()
            R.layout.data_input_tele -> setTele()
            R.layout.data_input_auto -> setInputAuto()
            R.layout.initialize -> setView(R.layout.initialize)
        }
    }
    fun setTele(view: View){
        setTele()
    }
    private fun setTele(){
        setView(R.layout.data_input_tele)
        runOnUiThread(Runnable {updateTele()})
    }
    fun updateTele(){
        var foulBTele = findViewById<TextView>(FoulBTele.id)
        var climbTitle = findViewById<TextView>(ClimbTitle.id)
        var lowGoalBT = findViewById<TextView>(LowGoalBT.id)
        var missedBT = findViewById<TextView>(MissedBT.id)
        var highGoalBT = findViewById<TextView>(HighGoalBT.id)

        foulBTele.text = "Foul: " + data.getFoul()
        climbTitle.text = "Climb: " + data.getClimb()
        lowGoalBT.text = "Low Goal: " + data.getTLow()
        highGoalBT.text = "High Goal: " + data.getTHigh()
        missedBT.text = "Missed: " + data.getMissedShotTele()
    }
    fun setInputAuto(view: View){

        setInputAuto()
    }
    private fun setInputAuto(){

        setView(R.layout.data_input_auto)
        currentView = R.layout.data_input_auto
        updateAuto()
    }
    fun updateAuto(){
        println("updateAuto")
        var foulBA = findViewById<TextView>(FoulBA.id)
        var lowGoalBA = findViewById<TextView>(LowGoalBA.id)
        var highGoalBA = findViewById<TextView>(HighGoalBA.id)
        var missedBA = findViewById<TextView>(MissedBA.id)
        var stealBA = findViewById<TextView>(StealBA.id)

               foulBA.setText("Foul:\n" + data.getFoul().toString())
               lowGoalBA.setText("Low Goal:\n" + data.getALow().toString())
               highGoalBA.setText("High Goal:\n" + data.getAHigh().toString())
               missedBA.setText("Missed:\n" + data.getMissedShotAuto().toString())
               stealBA.setText("Steal:\n" + data.getSteal().toString())

    }

    private fun updateScene(){
        println(currentView.toString() + " " + R.layout.data_input_auto)
        when(currentView){

            R.layout.data_input_auto -> setInputAuto()
            R.layout.data_input_tele -> setTele()
        }
        println(currentView.toString() + " " + R.layout.data_input_auto)
    }
    fun setView(view: Int){
        setContentView(view)
        println(view)
        currentView = view
    }
}


