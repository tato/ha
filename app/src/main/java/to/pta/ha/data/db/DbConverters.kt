package to.pta.ha.data.db

import androidx.room.TypeConverter
import java.time.LocalDate

class DbConverters {
    @TypeConverter
    fun fromDateString(value: String?): LocalDate? = value?.let { LocalDate.parse(it) }
    @TypeConverter
    fun fromLocalDate(value: LocalDate?): String? = value?.toString()
}