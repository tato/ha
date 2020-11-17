package to.pta.ado.ui

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import to.pta.ado.R
import to.pta.ado.data.db.AppDatabase
import to.pta.ado.data.model.Question

class ManageQuestionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_questions)
        BottomDrawerUtils.startBottomNavigationDrawer(this)

        val recycler = findViewById<RecyclerView>(R.id.questions)

        val db = AppDatabase.getInstance(this)
        val model: ManageQuestionsViewModel by viewModels { ManageQuestionsViewModel.Factory(db) }
        val questionsAdapter = QuestionsAdapter(ArrayList(), db) { dialog ->
            dialog.show(supportFragmentManager, "EditQuestionFragment")
        }
        model.questions.observe(this, {
            questionsAdapter.questions = it
            questionsAdapter.notifyDataSetChanged()
        })

        recycler.adapter = questionsAdapter
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val q = Question(0, "")
            val dialog = EditQuestionFragment(q) { db.questionDao().insert(it) }
            dialog.show(supportFragmentManager, "EditQuestionFragment")
        }
    }


    class QuestionsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val questionButton: Button = view.findViewById(R.id.question)
    }


    class QuestionsAdapter(
            var questions: List<Question>,
            private val db: AppDatabase,
            private val showDialog: (EditQuestionFragment) -> Unit,
    ) : RecyclerView.Adapter<QuestionsViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.element_question, parent, false)
            return QuestionsViewHolder(view)
        }

        override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
            holder.questionButton.text = questions[position].question
            holder.questionButton.setOnClickListener {
                val fragment = EditQuestionFragment(questions[position]) { db.questionDao().update(it) }
                showDialog(fragment)
            }
        }

        override fun getItemCount(): Int = questions.size
    }


    class EditQuestionFragment(private val question: Question, private val save: (Question) -> Unit) : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return activity?.let {
                val dialogView = requireActivity()
                        .layoutInflater
                        .inflate(R.layout.dialog_edit_question, null)
                val questionTextView = dialogView.findViewById<EditText>(R.id.question_text)
                questionTextView.setText(question.question, TextView.BufferType.EDITABLE)
                AlertDialog.Builder(it)
                        .setView(dialogView)
                        .setPositiveButton("Save") { _, _ ->
                            question.question = questionTextView.text.toString()
                            save(question)
                        }
                        .create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }
}