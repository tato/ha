package to.pta.ha.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import to.pta.ha.R
import to.pta.ha.data.db.AppDatabase
import to.pta.ha.data.model.Answer
import to.pta.ha.data.model.Question
import to.pta.ha.ui.AppViewModelFactory
import java.time.LocalDate

class HomeActivity : AppCompatActivity() {

    private val db by lazy { AppDatabase.getInstance(this) }
    private val model: HomeViewModel by viewModels { AppViewModelFactory(db) }

    private var debugStartButton: Button? = null
    private var yesNoQuestion: View? = null

    private var questions: List<Question> = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BottomDrawerUtils.startBottomNavigationDrawer(this)

        debugStartButton = findViewById(R.id.button_start_debug)
        yesNoQuestion = findViewById(R.id.yes_no_question)
        val yesButton = findViewById<Button>(R.id.ask_question_yes_button)
        val noButton = findViewById<Button>(R.id.ask_question_no_button)

        model.questions.observe(this) {
            this.questions = it
        }

        debugStartButton?.setOnClickListener {
            model.resetCurrentIndex()
            if (questions.isNotEmpty()) {
                debugStartButton?.visibility = View.GONE
                yesNoQuestion?.visibility = View.VISIBLE
            }
        }

        yesButton.setOnClickListener {
            yesNoButtonPressed(true)
        }
        noButton.setOnClickListener {
            yesNoButtonPressed(false)
        }

        val questionText = findViewById<TextView>(R.id.ask_question_yes_no_text)
        model.getCurrentIndex().observe(this) {
            if (it < questions.size) {
                questionText.text = questions[it].question
            }
        }
    }

    private fun yesNoButtonPressed(yesNoAnswer: Boolean) {
        val answer = Answer(
            null,
            yesNoAnswer,
            LocalDate.now(),
            questions[model.getCurrentIndex().value!!].id!!
        )
        db.answerDao().insert(answer)

        model.increaseCurrentIndex()
        if (model.areQuestionsFinished()) {
            debugStartButton?.visibility = View.VISIBLE
            yesNoQuestion?.visibility = View.GONE
        }
    }
}