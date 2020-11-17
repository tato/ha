package to.pta.ado.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "answers")
data class Answer(
    @PrimaryKey val id: Int,
    val answer: String,
    val time: LocalDate,
    val questionId: Int,
)