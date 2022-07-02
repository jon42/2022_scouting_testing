package com.example.test1

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        mAuth = FirebaseAuth.getInstance()
    }
    private val teamNumber: EditText = findViewById(R.id.AddTeamNumber)
    private val matchNumber: EditText = findViewById(R.id.addMatchNumber)
    public override fun onStart(){
        super.onStart()
//        var currentUser = mAuth.currentUser



    }

    fun bStart(view: View) {
        setContentView(R.layout.initialize)
    }
    fun bData(view: View){
//        if(teamNumber.text == null || matchNumber.text == null) return
        setContentView(R.layout.data_input)
//        var data: Data = Data(teamNumber.getText().toString().toInt(),"red", matchNumber.getText().toString().toInt())
    }
    fun setToData(){
        setContentView(R.layout.data_input)
    }

}