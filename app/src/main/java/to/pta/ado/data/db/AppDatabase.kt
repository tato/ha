package to.pta.ado.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import to.pta.ado.data.model.Answer
import to.pta.ado.data.model.Question

@Database(entities = [Question::class, Answer::class], version = 1)
@TypeConverters(DbConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}
