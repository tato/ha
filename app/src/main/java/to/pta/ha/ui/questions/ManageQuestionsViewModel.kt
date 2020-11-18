package to.pta.ha.ui.questions

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import to.pta.ha.data.db.AppDatabase
import to.pta.ha.data.model.Question

class ManageQuestionsViewModel(db: AppDatabase): ViewModel() {
    val questions: LiveData<List<Question>> = db.questionDao().getAll()
}