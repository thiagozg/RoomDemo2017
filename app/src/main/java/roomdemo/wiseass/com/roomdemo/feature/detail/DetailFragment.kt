package roomdemo.wiseass.com.roomdemo.feature.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_detail.*
import roomdemo.wiseass.com.roomdemo.R
import roomdemo.wiseass.com.roomdemo.R.id.lbl_date_and_time_header
import roomdemo.wiseass.com.roomdemo.base.BaseFragment
import roomdemo.wiseass.com.roomdemo.data.ListItem


/**
 * Created by thiagozg on 11/03/2018.
 */
class DetailFragment : BaseFragment() {

    companion object {
        const private val EXTRA_ITEM_ID = "EXTRA_ITEM_ID"

        fun newInstance(itemId: String): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putString(EXTRA_ITEM_ID, itemId)

            fragment.setArguments(args)
            return fragment
        }
    }

    private var itemId: String? = null

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.itemId = arguments?.getString(EXTRA_ITEM_ID)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        detailViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(DetailViewModel::class.java)

        detailViewModel.listItemById(itemId!!).observe(this, Observer<ListItem> {
            it?.let {
                lbl_date_and_time_header.setText(it.itemId)
                lbl_message_body.setText(it.message)
                imv_colored_background.setImageResource(it.colorResource)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }
}