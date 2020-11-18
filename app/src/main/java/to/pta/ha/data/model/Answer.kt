package to.pta.ha.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "answers")
data class Answer(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val yesNoAnswer: Boolean?,
    val date: LocalDate,
    val questionId: Int,
)