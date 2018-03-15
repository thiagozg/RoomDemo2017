package roomdemo.wiseass.com.roomdemo.feature.detail

import android.arch.lifecycle.ViewModel
import roomdemo.wiseass.com.roomdemo.data.ListItemRepository

/**
 * Created by thiagozg on 13/03/2018.
 */
class DetailViewModel (val repository: ListItemRepository) : ViewModel() {

    fun listItemById(itemId: String) = repository.getListItemById(itemId)

}