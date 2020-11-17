package to.pta.ado.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import to.pta.ado.data.db.AppDatabase
import to.pta.ado.data.model.Question

class ManageQuestionsViewModel(db: AppDatabase): ViewModel() {

    val questions: LiveData<List<Question>> = db.questionDao().getAll()

    class Factory(private val db: AppDatabase): ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            when (modelClass) {
                ManageQuestionsViewModel::class.java -> return ManageQuestionsViewModel(db) as T
                else -> throw IllegalStateException("Unexpected type in ViewModel factory: $modelClass")
            }
        }
    }
}