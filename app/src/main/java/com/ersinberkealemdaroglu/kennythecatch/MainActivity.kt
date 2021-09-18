package com.ersinberkealemdaroglu.kennythecatch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {


    var runnable : Runnable = Runnable {  }
    var runnableTime : Runnable = Runnable {  }
    var handler : Handler = Handler()
    var handlerTime : Handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var score = 0
        var time = 10
        var kenny = arrayOf(kenny1,kenny2,kenny3,kenny4,kenny5,kenny6,kenny7,kenny8,kenny9)


        //  bunlara örnek = array alınarak forda değişken.isVisible = false
        kenny1.isVisible = false
        kenny2.isVisible = false
        kenny3.isVisible = false
        kenny4.isVisible = false
        kenny5.isVisible = false
        kenny6.isVisible = false
        kenny7.isVisible = false
        kenny8.isVisible = false
        kenny9.isVisible = false



        for (kennys in kenny){
            kennys.setOnClickListener {
                if (time != 0){
                    score = score + 1
                }else{
                    kennys.isEnabled = false
                    score = score
                }

                scoreText.text = score.toString()
            }
        }

        runnableTime = object : Runnable{
            override fun run() {
                time = time - 1
                timeText.text = "Time : $time"
                handlerTime.postDelayed(this,1000)
            }

        }
        handlerTime.post(runnableTime)

        runnable = object : Runnable{
            override fun run() {
                handler.postDelayed(this,300)

                for (image in kenny){
                    image.isVisible = false
                }


                val random = Random()
                val randomIndex = random.nextInt(9)
                kenny[randomIndex].isVisible = true

                if (time == 0){
                    handler.removeCallbacks(runnable)
                    handlerTime.removeCallbacks(runnableTime)
                    val alert = AlertDialog.Builder(this@MainActivity)
                    alert.setTitle("Game Over")
                    alert.setMessage("Restart Game?")
                    alert.setPositiveButton("Yes"){dialog, which ->
                        val intent = intent
                        finish()
                        startActivity(intent)
                        Toast.makeText(this@MainActivity,"Game Restart",Toast.LENGTH_SHORT).show()
                    }
                    alert.setNegativeButton("No"){dialog, which ->
                        Toast.makeText(this@MainActivity,"Game Over",Toast.LENGTH_SHORT).show()
                    }
                    alert.show()
                }


            }


        }
        handler.post(runnable)



    }
}