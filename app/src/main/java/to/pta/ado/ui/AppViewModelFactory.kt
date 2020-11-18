package to.pta.ado.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import to.pta.ado.data.db.AppDatabase

class AppViewModelFactory(private val db: AppDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        when (modelClass) {
            ManageQuestionsViewModel::class.java -> return ManageQuestionsViewModel(db) as T
            HomeViewModel::class.java -> return HomeViewModel(db) as T
            else -> throw IllegalStateException("Unexpected type in ViewModel factory: $modelClass")
        }
    }
}