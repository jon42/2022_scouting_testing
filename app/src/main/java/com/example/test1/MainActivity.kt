package com.example.test1

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.data_input.*
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
        if(addTeamNumber.text.toString() == "" || addMatchNumber.text.toString() == "" ) return
        setContentView(R.layout.data_input)
        data = Data(addTeamNumber.getText().toString().toInt(),button.text.toString(), addMatchNumber.getText().toString().toInt())
        print(data.Match.toString() + " " + data.Team + " " + data.Pose)
    }
    fun add(view: View){
        var button = findViewById<Button>(view.id)

    }
    fun addC(view: View){
        var level = findViewById<Button>(view.id)
        data.setClimb(level.text.toString())
        climbTitle.text = "Climb: " + data.getClimb()
    }
    fun setToData(){
        setContentView(R.layout.data_input)
    }

}