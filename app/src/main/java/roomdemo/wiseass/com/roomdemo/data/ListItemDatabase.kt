package roomdemo.wiseass.com.roomdemo.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

/**
 * Created by thiagozg on 11/03/2018.
 */
@Database(entities = arrayOf(ListItem::class), version = 1)
abstract class ListItemDatabase : RoomDatabase() {

    abstract fun lisItemDao() : ListItemDao
}