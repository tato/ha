package to.pta.ado.ui.visualization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import to.pta.ado.R
import to.pta.ado.data.db.AppDatabase
import to.pta.ado.data.model.QuestionWithAnswers
import to.pta.ado.ui.AppViewModelFactory

class StatsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stats)

        val recycler = findViewById<RecyclerView>(R.id.stats_recycler)

        val db = AppDatabase.getInstance(this)
        val model: StatsViewModel by viewModels { AppViewModelFactory(db) }
        val questionsAdapter = StatsAdapter(ArrayList())
        model.questionsWithAnswers.observe(this, {
            questionsAdapter.questionsWithAnswers = it
            questionsAdapter.notifyDataSetChanged()
        })

        recycler.adapter = questionsAdapter
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }


    class StatsViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val elementText: TextView = view.findViewById(R.id.stats_element_text)
    }

    class StatsAdapter(
        var questionsWithAnswers: List<QuestionWithAnswers>
    ): RecyclerView.Adapter<StatsViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.element_stats, parent, false)
            return StatsViewHolder(view)
        }

        override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
            val questionWithAnswers = questionsWithAnswers[position]
            var elementText = "\"" + questionWithAnswers.question.question + "\""
            for (answer in questionWithAnswers.answers) {
                val answerText = if (answer.yesNoAnswer == true) {
                    "Yes"
                } else {
                    "No"
                }
                elementText += "\n  - (${answer.date}) $answerText"
            }
            holder.elementText.text = elementText
        }

        override fun getItemCount(): Int = questionsWithAnswers.size
    }
}