package to.pta.ado.ui.visualization

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import to.pta.ado.data.db.AppDatabase
import to.pta.ado.data.model.Question
import to.pta.ado.data.model.QuestionWithAnswers

class StatsViewModel(db: AppDatabase): ViewModel() {
    val questionsWithAnswers: LiveData<List<QuestionWithAnswers>> = db.questionDao().getQuestionsWithAnswers()
}