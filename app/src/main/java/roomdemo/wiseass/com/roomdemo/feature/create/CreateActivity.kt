package roomdemo.wiseass.com.roomdemo.feature.create

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import roomdemo.wiseass.com.roomdemo.R
import roomdemo.wiseass.com.roomdemo.util.addFragment


/**
 * Created by thiagozg on 11/03/2018.
 */
class CreateActivity : AppCompatActivity() {

    companion object {
        const private val CREATE_FRAG = "CREATE_FRAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        val fragment: CreateFragment? = supportFragmentManager.findFragmentByTag(CREATE_FRAG) as? CreateFragment
        addFragment(supportFragmentManager, fragment ?: CreateFragment.newInstance(),
                R.id.root_activity_create,
                CREATE_FRAG
        )

    }
}