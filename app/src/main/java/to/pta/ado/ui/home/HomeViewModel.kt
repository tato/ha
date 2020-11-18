package to.pta.ado.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import to.pta.ado.data.db.AppDatabase
import to.pta.ado.data.model.Question

class HomeViewModel(db: AppDatabase): ViewModel() {

    val questions: LiveData<List<Question>> = db.questionDao().getAll()

    private var currentIndex: MutableLiveData<Int> = MutableLiveData(0)
    fun getCurrentIndex(): LiveData<Int> = currentIndex
    fun resetCurrentIndex() {
        currentIndex.value = 0
    }
    fun increaseCurrentIndex() {
        if (currentIndex.value == null)
            currentIndex.value = 0
        else {
            currentIndex.value = currentIndex.value!! + 1
        }
    }
    fun areQuestionsFinished(): Boolean = currentIndex.value ?: -1 >= questions.value?.size ?: 0
}