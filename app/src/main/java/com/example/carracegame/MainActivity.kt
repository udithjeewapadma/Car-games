package com.example.carracegame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity(), GameTask {
    lateinit var rootLayout: LinearLayout
    lateinit var startBtn: Button
    lateinit var restartBtn: Button
    lateinit var mGameView: GameView
    lateinit var score: TextView
    var currentScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        startBtn = findViewById(R.id.startBtn)
        restartBtn = findViewById(R.id.restartBtn)
        rootLayout = findViewById(R.id.rootLayout)
        score = findViewById(R.id.score)
        mGameView = GameView(this, this)

        // Set click listeners
        startBtn.setOnClickListener {
            startGame()
        }

        restartBtn.setOnClickListener {
            restartGame()
        }

        // Initially, only start button and score are visible
        restartBtn.visibility = View.GONE
        score.visibility = View.GONE
    }

    private fun startGame() {
        // Initialize game view
        mGameView.setBackgroundResource(R.drawable.road_0)
        rootLayout.addView(mGameView)

        // Hide buttons and score
        startBtn.visibility = View.GONE
        restartBtn.visibility = View.GONE
        score.visibility = View.GONE

        // Reset score
        currentScore = 0
    }
    private fun restartGame() {
        // Remove the current instance of GameView
        rootLayout.removeView(mGameView)

        // Create a new instance of GameView
        mGameView = GameView(this, this)

        // Start the game again
        startGame()
    }


    override fun closeGame(mScore: Int) {
        // Update score and show buttons
        currentScore = mScore
        score.text = "Score : $currentScore"
        rootLayout.removeView(mGameView)
        startBtn.visibility = View.VISIBLE
        restartBtn.visibility = View.VISIBLE
        score.visibility = View.VISIBLE
    }
}

