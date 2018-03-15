package roomdemo.wiseass.com.roomdemo.feature.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import roomdemo.wiseass.com.roomdemo.R
import roomdemo.wiseass.com.roomdemo.util.addFragment

/**
 * Created by thiagozg on 12/03/2018.
 */

class ListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val fragment = supportFragmentManager.findFragmentByTag(LIST_FRAG)
        addFragment(supportFragmentManager,
                fragment ?: ListFragment.newInstance(),
                R.id.root_activity_list, LIST_FRAG)
    }

    companion object {
        const private val LIST_FRAG = "LIST_FRAG"
    }

}