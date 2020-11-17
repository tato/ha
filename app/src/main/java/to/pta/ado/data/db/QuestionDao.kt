package to.pta.ado.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import to.pta.ado.data.model.Question
import to.pta.ado.data.model.QuestionWithAnswers

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions")
    fun getAll(): LiveData<List<Question>>

//    @Transaction
//    @Query("SELECT * FROM questions")
//    fun getQuestionsWithAnswers(): List<QuestionWithAnswers>

    @Insert
    fun insert(question: Question): Long

    @Update
    fun update(vararg question: Question)

    @Delete
    fun delete(vararg question: Question)
}