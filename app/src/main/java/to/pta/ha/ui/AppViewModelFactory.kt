package to.pta.ha.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import to.pta.ha.data.db.AppDatabase
import to.pta.ha.ui.home.HomeViewModel
import to.pta.ha.ui.questions.ManageQuestionsViewModel
import to.pta.ha.ui.visualization.StatsViewModel

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