package to.pta.ado.data.db

import androidx.room.Dao
import androidx.room.Insert
import to.pta.ado.data.model.Answer

@Dao
interface AnswerDao {
    @Insert
    fun insert(answer: Answer): Long
}