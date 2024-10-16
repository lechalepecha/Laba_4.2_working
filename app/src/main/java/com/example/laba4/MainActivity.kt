package com.example.laba4

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.widget.TextView
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


private const val TAG="MainActivity"
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    var id_Quistion=1
    private val quizViewModel:QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex
        val provider: ViewModelProvider = ViewModelProvider(this)
        val quizViewModel = provider.get(QuizViewModel::class.java)
        Log.d(TAG, "Got a QuizViewModel:$quizViewModel")

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
            trueButton.visibility= View.INVISIBLE
            falseButton.visibility= View.INVISIBLE
        }
        falseButton.setOnClickListener()
        {
            checkAnswer(false)
            trueButton.visibility= View.INVISIBLE
            falseButton.visibility= View.INVISIBLE
        }
        nextButton.setOnClickListener()
        {
            quizViewModel.moveToNext()
            updateQuestion()
            trueButton.visibility= View.VISIBLE
            falseButton.visibility= View.VISIBLE
            id_Quistion++
            if(id_Quistion==6)
                nextButton.visibility=View.INVISIBLE
        }
        updateQuestion()
    }

    override fun onStart()
    {
        super.onStart()
        Log.d(TAG,"onStart() called")
    }
    override fun onResume()
    {
        super.onResume()
        Log.d(TAG,"onResume() called")
    }
    override fun onPause()
    {
        super.onPause()
        Log.d(TAG,"onPause() called")
    }
    override fun onSaveInstanceState(savedInstanceState: Bundle)
    {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG,"onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX,quizViewModel.currentIndex)
    }
    override fun onStop()
    {
        super.onStop()
        Log.d(TAG,"onStop() called")
    }
    override fun onDestroy()
    {
        super.onDestroy()
        Log.d(TAG,"onDestroy() called")
    }

    private fun updateQuestion(){
        val questionTextResId=quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer:Boolean)
    {
        val correctAnswer=quizViewModel.currentQuestionAnswer
        val messageResId= if (userAnswer==correctAnswer)
        {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        Toast.makeText(this,messageResId,Toast.LENGTH_SHORT).show()
        //updateQuestion()
    }

}