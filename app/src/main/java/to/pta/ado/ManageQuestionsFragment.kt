package to.pta.ado

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ManageQuestionsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_manage_questions, container, false)

        val recycler = view.findViewById<RecyclerView>(R.id.questions)

        val questionsAdapter = QuestionsAdapter(arrayListOf("Did you run?", "Did you sleep?", "Did you write?")) { dialog ->
            activity?.let {
                dialog.show(it.supportFragmentManager, "EditQuestionFragment")
            } ?: throw IllegalStateException("Activity can't be null at View creation time")
        }
        recycler.adapter = questionsAdapter
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            questionsAdapter.addQuestion("")
            // TODO when I'm saving daily data, I need to migrate old data from this question to the new formulation somehow
            val dialog = EditQuestionFragment(questionsAdapter, questionsAdapter.itemCount - 1)
            activity?.let {
                dialog.show(it.supportFragmentManager, "EditQuestionFragment")
            } ?: throw IllegalStateException("Activity can't be null at View creation time")
        }

        return view
    }


    class QuestionsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val questionButton: Button = view.findViewById(R.id.question)
    }


    class QuestionsAdapter(
            private val questions: ArrayList<String>,
            private val showDialog: (EditQuestionFragment) -> Unit
    ) : RecyclerView.Adapter<QuestionsViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.element_question, parent, false)
            return QuestionsViewHolder(view)
        }

        override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
            holder.questionButton.text = questions[position]
            holder.questionButton.setOnClickListener {
                val fragment = EditQuestionFragment(this, position)
                showDialog(fragment)
            }
        }

        override fun getItemCount(): Int = questions.size

        fun getQuestion(position: Int): String {
            return questions[position]
        }

        fun removeQuestion(position: Int) {
            questions.removeAt(position)
            notifyDataSetChanged()
        }

        fun setQuestion(questionText: String, position: Int) {
            questions[position] = questionText
            notifyItemChanged(position)
        }

        fun addQuestion(questionText: String) {
            questions.add(questionText)
        }
    }


    class EditQuestionFragment(private val adapter: QuestionsAdapter, private val position: Int) : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return activity?.let {
                val dialogView = requireActivity()
                        .layoutInflater
                        .inflate(R.layout.dialog_edit_question, null)
                val questionTextView = dialogView.findViewById<EditText>(R.id.question_text)
                questionTextView.setText(adapter.getQuestion(position), TextView.BufferType.EDITABLE)
                AlertDialog.Builder(it)
                        .setView(dialogView)
                        .setPositiveButton("Save") { _, _ ->
                            adapter.setQuestion(questionTextView.text.toString(), position)
                            // TODO when I'm saving daily data, I need to migrate old data from this question to the new formulation somehow
                        }
                        .setNegativeButton("Delete") { _, _ ->
                            adapter.removeQuestion(position)
                            // TODO when I'm saving daily data, I need to migrate old data from this question to the new formulation somehow
                        }
                        .create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }
}