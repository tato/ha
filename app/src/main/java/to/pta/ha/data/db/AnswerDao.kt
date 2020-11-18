package to.pta.ha.data.db

import androidx.room.Dao
import androidx.room.Insert
import to.pta.ha.data.model.Answer

@Dao
interface AnswerDao {
    @Insert
    fun insert(answer: Answer): Long
}