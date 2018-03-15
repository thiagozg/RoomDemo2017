package roomdemo.wiseass.com.roomdemo.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import roomdemo.wiseass.com.roomdemo.data.ListItemRepository
import roomdemo.wiseass.com.roomdemo.feature.list.ListViewModel
import roomdemo.wiseass.com.roomdemo.feature.detail.DetailViewModel
import roomdemo.wiseass.com.roomdemo.feature.create.CreateViewModel
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by thiagozg on 12/03/2018.
 */
@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory
@Inject constructor(private val repository: ListItemRepository) :
        ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ListViewModel::class.java) ->
                ListViewModel(repository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) ->
                DetailViewModel(repository) as T
            modelClass.isAssignableFrom(CreateViewModel::class.java) ->
                CreateViewModel(repository) as T
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }

}