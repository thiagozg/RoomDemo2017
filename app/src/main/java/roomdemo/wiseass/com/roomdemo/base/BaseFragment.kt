package roomdemo.wiseass.com.roomdemo.base

import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.support.v4.app.Fragment
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by thiagozg on 14/03/2018.
 */
abstract class BaseFragment : Fragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

}