package roomdemo.wiseass.com.roomdemo.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by thiagozg on 11/03/2018.
 */

fun addFragment(manager: FragmentManager, fragment: Fragment,
                                  frameId: Int, tag: String) {
    val transaction = manager.beginTransaction()
    transaction.replace(frameId, fragment, tag)
    transaction.commit()
}