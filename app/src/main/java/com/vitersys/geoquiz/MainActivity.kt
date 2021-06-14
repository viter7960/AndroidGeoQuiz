package com.vitersys.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var trueButton : Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var previousButton: ImageButton
    private lateinit var questionTextView: TextView

    private var currentIndex = 0;
    private val bankOfQuestions = listOf(
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_oceans, true)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate() called")

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        previousButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.quiz_question)

        questionTextView.setOnClickListener { view: View ->
            currentIndex = (currentIndex + 1) % bankOfQuestions.size
            updateQuestion()
        }

        trueButton.setOnClickListener { view: View ->
            checkAnswer(true)
        }

        falseButton.setOnClickListener { view: View ->
            checkAnswer(false)
        }

        nextButton.setOnClickListener { view: View? ->
            currentIndex = (currentIndex + 1) % bankOfQuestions.size
            updateQuestion()
        }

        previousButton.setOnClickListener { view: View ->
            val calculatedIndex = if (currentIndex > 0)
                    (currentIndex - 1) % bankOfQuestions.size
            else
                bankOfQuestions.size - 1
            currentIndex = (calculatedIndex)
            updateQuestion()
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = bankOfQuestions[currentIndex].userAnswer
        val answerResId = if (userAnswer == correctAnswer) {
            R.string.correct_answer
        } else {
            R.string.incorrect_answer
        }

        var toast = Toast.makeText(this, answerResId, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP, 20, 10)
        toast.show()
    }

    private fun updateQuestion() {
        val currentQuestionRId = bankOfQuestions[currentIndex].questionTextResId;
        questionTextView.setText(currentQuestionRId)
    }
}