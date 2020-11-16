package to.pta.ado

import android.os.Bundle
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

const val TAG = "ManageQuestionsFragment"

class ManageQuestionsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_manage_questions, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.questions)
        recycler.adapter = QuestionsAdapter(arrayOf("Did you run?", "Did you sleep?", "Did you write?"))
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        return view
    }

    class QuestionsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val questionText: TextView = view.findViewById(R.id.question)
    }

    class QuestionsAdapter(private val questions: Array<String>) : RecyclerView.Adapter<QuestionsViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.element_question, parent, false)
            return QuestionsViewHolder(view)
        }

        override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
            Log.i(TAG, "entered onBindViewHolder")
            holder.questionText.text = questions[position]
        }

        override fun getItemCount(): Int = questions.size
    }
}