package to.pta.ado.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import to.pta.ado.data.db.AppDatabase
import to.pta.ado.ui.home.HomeViewModel
import to.pta.ado.ui.questions.ManageQuestionsViewModel
import to.pta.ado.ui.visualization.StatsViewModel

class AppViewModelFactory(private val db: AppDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return when (modelClass) {
            ManageQuestionsViewModel::class.java -> ManageQuestionsViewModel(db) as T
            HomeViewModel::class.java -> HomeViewModel(db) as T
            StatsViewModel::class.java -> StatsViewModel(db) as T
            else -> throw IllegalStateException("Unexpected type in ViewModel factory: $modelClass")
        }
    }
}