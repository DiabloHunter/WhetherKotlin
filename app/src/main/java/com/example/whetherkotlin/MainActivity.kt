package com.example.whetherkotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.doAsync
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {

    private var userField: EditText? = null
    private var mainButton: Button? = null
    private var resultInfo: TextView? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userField = findViewById(R.id.user_field)
        mainButton = findViewById(R.id.main_button)
        resultInfo = findViewById(R.id.result_info)

        mainButton?.setOnClickListener{
            if(userField?.text?.toString()?.trim()?.equals("")!!){
                Toast.makeText(this, "Enter the city", Toast.LENGTH_LONG).show()
            }
            else{
                val city:String = userField?.text.toString()
                val key = "d5b9215f7a2f4e8ee31d20b3fe6cb98e"
                val url ="https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$key&units=metric&lang=ru"


                doAsync {
                    val apiResponse = URL(url).readText()

                    val weather = JSONObject(apiResponse).getJSONArray("weather")
                    val desc = weather.getJSONObject(0).getString("description")

                    val main = JSONObject(apiResponse).getJSONObject("main")
                    val temp = main.getString("temp")

                    resultInfo?.text = "Temperature: $temp\n$desc"
                }
            }
        }
    }
}