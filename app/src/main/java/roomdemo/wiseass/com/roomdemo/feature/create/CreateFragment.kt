package roomdemo.wiseass.com.roomdemo.feature.create

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_create.*
import roomdemo.wiseass.com.roomdemo.R
import roomdemo.wiseass.com.roomdemo.base.BaseFragment
import roomdemo.wiseass.com.roomdemo.data.ListItem
import roomdemo.wiseass.com.roomdemo.feature.list.ListActivity
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by thiagozg on 11/03/2018.
 */
class CreateFragment : BaseFragment() {
    
    companion object {
        fun newInstance() = CreateFragment()
    }

    private lateinit var createViewModel: CreateViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CreateViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imb_create_back!!.setOnClickListener { startListActivity() }
        imb_create_done.setOnClickListener {
            val listItem = ListItem(
                    getDate(),
                    edt_create_message.text.toString(),
                    getDrawableResource(vp_create_drawable.currentItem)
            )
            createViewModel.addNewItem(listItem)
            startListActivity()
        }

        vp_create_drawable.adapter = DrawablePagerAdapter()
        vpi_create_drawable.setViewPager(vp_create_drawable)
    }

    fun getDrawableResource(pagerItemPosition: Int): Int {
        when (pagerItemPosition) {
            0 -> return R.drawable.red_drawable
            1 -> return R.drawable.blue_drawable
            2 -> return R.drawable.green_drawable
            3 -> return R.drawable.yellow_drawable
            else -> return 0
        }
    }

    private fun startListActivity() {
        startActivity(Intent(activity, ListActivity::class.java))
    }

    private inner class DrawablePagerAdapter : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(activity)
            val pagerItem = inflater.inflate(R.layout.item_drawable_pager,
                    container,
                    false) as ImageView

            when (position) {
                0 -> pagerItem.setImageResource(R.drawable.red_drawable)
                1 -> pagerItem.setImageResource(R.drawable.blue_drawable)
                2 -> pagerItem.setImageResource(R.drawable.green_drawable)
                3 -> pagerItem.setImageResource(R.drawable.yellow_drawable)
            }

            container.addView(pagerItem)
            return pagerItem
        }

        override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
            collection.removeView(view as View)
        }

        override fun getCount(): Int {
            return 4
        }

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }
    }

    fun getDate(): String {
        val currentDate = Calendar.getInstance().getTime()
        val format = SimpleDateFormat("yyyy/MM/dd/kk:mm:ss")
        return format.format(currentDate)
    }
}