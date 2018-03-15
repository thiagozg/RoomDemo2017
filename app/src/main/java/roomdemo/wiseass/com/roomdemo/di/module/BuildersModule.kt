package roomdemo.wiseass.com.roomdemo.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import roomdemo.wiseass.com.roomdemo.feature.create.CreateFragment
import roomdemo.wiseass.com.roomdemo.feature.detail.DetailFragment
import roomdemo.wiseass.com.roomdemo.feature.list.ListFragment

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector
    internal abstract fun bindListFragment(): ListFragment

    @ContributesAndroidInjector
    internal abstract fun bindCreateFragment(): CreateFragment

    @ContributesAndroidInjector
    internal abstract fun bindDetaiFragment(): DetailFragment

}
