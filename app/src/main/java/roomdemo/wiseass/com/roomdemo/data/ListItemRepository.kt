package roomdemo.wiseass.com.roomdemo.data

import android.arch.lifecycle.LiveData
import javax.inject.Inject

/**
 * Created by thiagozg on 11/03/2018.
 */
class ListItemRepository
@Inject constructor(private val listItemDao: ListItemDao) {

    fun getListItems() : LiveData<MutableList<ListItem>> = listItemDao.listItems

    fun getListItemById(itemId: String) : LiveData<ListItem> = listItemDao.getListItemById(itemId)

    fun deleteListItem(listItem: ListItem) = listItemDao.deleteListItem(listItem)

    fun insertListItem(listItem: ListItem) = listItemDao.insertListItem(listItem)

}