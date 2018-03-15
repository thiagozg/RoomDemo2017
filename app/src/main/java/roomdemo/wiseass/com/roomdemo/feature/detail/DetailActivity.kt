package roomdemo.wiseass.com.roomdemo.feature.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import roomdemo.wiseass.com.roomdemo.R
import roomdemo.wiseass.com.roomdemo.util.addFragment


class DetailActivity : AppCompatActivity() {

    companion object {
        const private val EXTRA_ITEM_ID = "EXTRA_ITEM_ID"
        const private val DETAIL_FRAG = "DETAIL_FRAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        if (intent.hasExtra(EXTRA_ITEM_ID)) {
            val itemId = intent.getStringExtra(EXTRA_ITEM_ID)

            val fragment: DetailFragment? = supportFragmentManager.findFragmentByTag(DETAIL_FRAG) as? DetailFragment
            addFragment(supportFragmentManager, fragment ?: DetailFragment.newInstance(itemId),
                    R.id.root_activity_detail, DETAIL_FRAG)

        } else {
            Toast.makeText(this, R.string.error_no_extra_found, Toast.LENGTH_LONG).show()
        }

    }
}
