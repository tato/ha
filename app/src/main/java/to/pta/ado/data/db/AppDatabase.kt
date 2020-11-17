package to.pta.ado.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import to.pta.ado.data.model.Answer
import to.pta.ado.data.model.Question

@Database(entities = [Question::class, Answer::class], version = 1, exportSchema = false)
@TypeConverters(DbConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    companion object {
        @Volatile private var instance: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            val i = instance
            if (i != null) {
                return i
            }
            return synchronized(this) {
                val y = instance
                if (y != null) {
                    y
                } else {
                    val created = Room
                        .databaseBuilder(
                                context.applicationContext,
                                AppDatabase::class.java,
                                "do.db",
                        )
                        .allowMainThreadQueries()
                        .build()
                    instance = created
                    created
                }
            }
        }
    }
}
