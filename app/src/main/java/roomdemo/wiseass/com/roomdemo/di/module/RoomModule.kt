package roomdemo.wiseass.com.roomdemo.di.module

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import dagger.Module
import dagger.Provides
import roomdemo.wiseass.com.roomdemo.CustomApplication
import roomdemo.wiseass.com.roomdemo.data.ListItemDao
import roomdemo.wiseass.com.roomdemo.data.ListItemDatabase
import roomdemo.wiseass.com.roomdemo.data.ListItemRepository
import roomdemo.wiseass.com.roomdemo.base.ViewModelFactory
import javax.inject.Singleton


/**
 * Created by thiagozg on 12/03/2018.
 */
@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideListItemDatabase(application: CustomApplication) =
            Room.databaseBuilder(
                    application,
                    ListItemDatabase::class.java,
                    "ListItem.db"
            ).build()

    @Provides
    @Singleton
    fun provideListItemDao(database: ListItemDatabase) = database.lisItemDao()

    @Provides
    @Singleton
    fun provideListItemRepository(listItemDao: ListItemDao) = ListItemRepository(listItemDao)

    @Provides
    @Singleton
    fun provideViewModelFactory(repository: ListItemRepository): ViewModelProvider.Factory =
            ViewModelFactory(repository)

}