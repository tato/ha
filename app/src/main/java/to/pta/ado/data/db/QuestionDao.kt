package to.pta.ado.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import to.pta.ado.data.model.Question
import to.pta.ado.data.model.QuestionWithAnswers

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions")
    fun getAll(): List<Question>

    @Transaction
    @Query("SELECT * FROM questions")
    fun getQuestionsWithAnswers(): List<QuestionWithAnswers>
}