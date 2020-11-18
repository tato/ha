package to.pta.ha.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class Question(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    var question: String,
) {
    var deleted: Boolean = false
}