package roomdemo.wiseass.com.roomdemo.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import roomdemo.wiseass.com.roomdemo.CustomApplication
import roomdemo.wiseass.com.roomdemo.di.module.*
import javax.inject.Singleton


/**
 * Created by thiagozg on 12/03/2018.
 */
@Singleton
@Component(modules = arrayOf(
        AndroidSupportInjectionModule::class,
        RoomModule::class,
        BuildersModule::class)
)
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: CustomApplication): Builder
        fun build(): ApplicationComponent
    }

    fun inject(application: CustomApplication)
}