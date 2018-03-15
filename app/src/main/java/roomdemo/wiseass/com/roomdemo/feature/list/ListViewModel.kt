package roomdemo.wiseass.com.roomdemo.feature.list

import android.arch.lifecycle.ViewModel
import org.jetbrains.anko.doAsync
import roomdemo.wiseass.com.roomdemo.data.ListItem
import roomdemo.wiseass.com.roomdemo.data.ListItemRepository

/**
 * Created by thiagozg on 12/03/2018.
 */
class ListViewModel(val repository: ListItemRepository) : ViewModel() {

    fun listItems() = repository.getListItems()

    // Could Use RxJava here
    fun deleteItems(listItem: ListItem) = doAsync { repository.deleteListItem(listItem) }
}