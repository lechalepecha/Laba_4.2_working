package com.example.laba4

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    private  val questionBank= listOf(Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast,false),
        Question(R.string.question_africa,false),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia,true))
    private var currentIndex=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        trueButton=findViewById(R.id.button_true)
        falseButton=findViewById(R.id.button_false)
        nextButton=findViewById(R.id.button_next)
        questionTextView=findViewById(R.id.question)


        trueButton.setOnClickListener()
        {
            checkAnswer(true)
        }
        falseButton.setOnClickListener()
        {
            checkAnswer(false)
        }
        nextButton.setOnClickListener()
        {
            currentIndex=(currentIndex+1)%questionBank.size
            updateQuestin()
        }
        updateQuestin()
    }
    private fun updateQuestin(){
        val questionTextResId=questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer:Boolean)
    {
        val correctAnswer=questionBank[currentIndex].answer
        val messageResId= if (userAnswer==correctAnswer)
        {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show()
    }

}