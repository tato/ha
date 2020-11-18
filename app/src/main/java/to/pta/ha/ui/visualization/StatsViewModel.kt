package to.pta.ha.ui.visualization

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import to.pta.ha.data.db.AppDatabase
import to.pta.ha.data.model.QuestionWithAnswers

class StatsViewModel(db: AppDatabase): ViewModel() {
    val questionsWithAnswers: LiveData<List<QuestionWithAnswers>> = db.questionDao().getQuestionsWithAnswers()
}