package roomdemo.wiseass.com.roomdemo.feature.list

import android.annotation.TargetApi
import android.app.ActivityOptions
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.transition.Fade
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.fragment_list.*
import roomdemo.wiseass.com.roomdemo.R
import roomdemo.wiseass.com.roomdemo.base.BaseFragment
import roomdemo.wiseass.com.roomdemo.feature.create.CreateActivity
import roomdemo.wiseass.com.roomdemo.data.ListItem
import roomdemo.wiseass.com.roomdemo.feature.detail.DetailActivity


class ListFragment : BaseFragment() {

    private lateinit var listViewModel: ListViewModel

    private var listOfData: MutableList<ListItem>? = null
    private val adapter: CustomAdapter? = null

    /*------------------------------- Lifecycle -------------------------------*/

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //Set up and subscribe (observe) to the ViewModel
        listViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ListViewModel::class.java)

        listViewModel.listItems().observe(this, Observer<MutableList<ListItem>> {
            if (this@ListFragment.listOfData == null) {
                setListData(it)
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tlb_list_activity.setTitle(R.string.title_tlb_list_activity)
        tlb_list_activity.setLogo(R.drawable.ic_view_list_white_24dp)
        tlb_list_activity.setTitleMarginStart(72)

        fab_create_new_item.setOnClickListener{ startCreateActivity() }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun startDetailActivity(itemId: String, viewRoot: View) {
        val container = activity
        val i = Intent(container, DetailActivity::class.java)
        i.putExtra(EXTRA_ITEM_ID, itemId)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            container!!.window.enterTransition = Fade(Fade.IN)
            container.window.enterTransition = Fade(Fade.OUT)

            val options = ActivityOptions
                    .makeSceneTransitionAnimation(container,
                            Pair(viewRoot.findViewById(R.id.imv_list_item_circle),
                                    getString(R.string.transition_drawable)),
                            Pair(viewRoot.findViewById(R.id.lbl_message),
                                    getString(R.string.transition_message)),
                            Pair(viewRoot.findViewById(R.id.lbl_date_and_time),
                                    getString(R.string.transition_time_and_date)))

            startActivity(i, options.toBundle())

        } else {
            startActivity(i)
        }
    }

    fun startCreateActivity() {
        startActivity(Intent(activity, CreateActivity::class.java))
    }


    fun setListData(listOfData: MutableList<ListItem>?) {
        this.listOfData = listOfData

        val layoutManager = LinearLayoutManager(activity)
        rec_list_activity.layoutManager = layoutManager
        val adapter = CustomAdapter()
        rec_list_activity.adapter = adapter

        val itemDecoration = DividerItemDecoration(rec_list_activity.context, layoutManager.orientation)

        val drawable = ContextCompat.getDrawable(activity!!, R.drawable.divider_white)
        drawable?.let {itemDecoration.setDrawable(it) }
        rec_list_activity.addItemDecoration(itemDecoration)

        val itemTouchHelper = ItemTouchHelper(createHelperCallback())
        itemTouchHelper.attachToRecyclerView(rec_list_activity)
    }


    /*-------------------- RecyclerView Boilerplate ----------------------*/

    private inner class CustomAdapter : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>() {//6

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val v = this@ListFragment.activity!!.layoutInflater!!.inflate(R.layout.item_data, parent, false)
            return CustomViewHolder(v)
        }

        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            holder.bindData(listOfData!![position])
        }

        override fun getItemCount(): Int {
            return listOfData!!.size
        }

        private inner class CustomViewHolder(itemView: View) :
                RecyclerView.ViewHolder(itemView), View.OnClickListener {

            private val coloredCircle: CircleImageView
            private val dateAndTime: TextView
            private val message: TextView
            private val loading: ProgressBar
            private val container: ViewGroup

            init {
                this.coloredCircle = itemView.findViewById(R.id.imv_list_item_circle)
                this.dateAndTime = itemView.findViewById(R.id.lbl_date_and_time)
                this.message = itemView.findViewById(R.id.lbl_message)
                this.loading = itemView.findViewById(R.id.pro_item_data)
                this.container = itemView.findViewById(R.id.root_list_item)
                this.container.setOnClickListener(this)
            }

            internal fun bindData(currentItem: ListItem) {
                coloredCircle.setImageResource(currentItem.colorResource)
                message.setText(currentItem.message)
                dateAndTime.setText(currentItem.itemId)
                loading.visibility = View.INVISIBLE
            }

            override fun onClick(v: View) {
                val listItem = listOfData!![this.adapterPosition]
                startDetailActivity(listItem.itemId, v)
            }
        }

    }

    private fun createHelperCallback(): ItemTouchHelper.Callback {
        return object : ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            //not used, as the first parameter above is 0
            override fun onMove(rec_list_activity: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                listOfData?.let {
                    listViewModel.deleteItems(it[position])

                    //ensure View is consistent with underlying data
                    it.removeAt(position)
                    adapter?.notifyItemRemoved(position)
                }
            }
        }
    }

    companion object {
        const private val EXTRA_ITEM_ID = "EXTRA_ITEM_ID"

        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }
}
