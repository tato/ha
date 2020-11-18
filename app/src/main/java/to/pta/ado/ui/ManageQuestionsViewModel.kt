package to.pta.ado.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import to.pta.ado.data.db.AppDatabase
import to.pta.ado.data.model.Question

class ManageQuestionsViewModel(db: AppDatabase): ViewModel() {
    val questions: LiveData<List<Question>> = db.questionDao().getAll()
}