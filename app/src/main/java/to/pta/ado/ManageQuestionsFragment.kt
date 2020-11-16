package to.pta.ado

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ManageQuestionsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_manage_questions, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.questions)
        recycler.adapter = QuestionsAdapter(arrayOf("Did you run?", "Did you sleep?", "Did you write?")) {
            activity?.let {
                EditQuestionFragment().show(it.supportFragmentManager, "EditQuestionFragment")
            } ?: throw IllegalStateException("Activity can't be null at View creation time")
        }
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return view
    }

    class QuestionsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val questionButton: Button = view.findViewById(R.id.question)
    }

    class QuestionsAdapter(private val questions: Array<String>, private val callback: (View) -> Unit) : RecyclerView.Adapter<QuestionsViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.element_question, parent, false)
            return QuestionsViewHolder(view)
        }

        override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
            holder.questionButton.text = questions[position]
            holder.questionButton.setOnClickListener(callback)
        }

        override fun getItemCount(): Int = questions.size
    }

    class EditQuestionFragment: DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return activity?.let {
                val builder = AlertDialog.Builder(it)
                builder.setMessage("Fire missiles?")
                        .setPositiveButton("YEEE") { _, _ ->
                            Toast.makeText(it, "FIRE!", Toast.LENGTH_LONG).show()
                        }
                        .setNegativeButton("Naaaa") { _, _ ->
                            Toast.makeText(it, "oh", Toast.LENGTH_LONG).show()
                        }
                builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")
        }
    }
}