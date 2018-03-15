package roomdemo.wiseass.com.roomdemo.feature.create

import android.arch.lifecycle.ViewModel
import org.jetbrains.anko.doAsync
import roomdemo.wiseass.com.roomdemo.data.ListItem
import roomdemo.wiseass.com.roomdemo.data.ListItemRepository

/**
 * Created by thiagozg on 12/03/2018.
 */
class CreateViewModel(val repository: ListItemRepository) : ViewModel() {

    // Could Use RxJava here
    fun addNewItem(listItem: ListItem) = doAsync { repository.insertListItem(listItem) }
}