package com.watkins.lisa.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.watkins.lisa.BaseApplication
import com.watkins.lisa.data.AppDatabase
import com.watkins.lisa.util.ConnectivityLiveData
import com.watkins.lisa.util.Logger
import dagger.*
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * [AndroidInjectionModule] provides bindings for all base Android classes
 */
@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    ActivityModule::class]
)
interface ApplicationComponent {
    /**
     * Defines the builder class that Dagger generates for this component. You can enforce here what dagger needs to create
     * the root of the graph, in this case we only need an [Application] instance.
     */
    @Component.Builder
    interface Builder {
        /**
         * Binds the instance of an application to the root component of the graph.
         */
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: BaseApplication)
}

/**
 * Application level dependencies. Notice that [ViewModelModule] is injected here, so we are dealing with one instance
 * of the factory for the scope of the application.
 */
@Module(includes = [
    ViewModelModule::class
])
object ApplicationModule {
    @Singleton
    @Provides
    @JvmStatic
    fun providesContext(application: Application): Context {
        return application
    }

    @Provides
    @JvmStatic
    fun providesConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    // TODO: Rename database
    @Singleton
    @Provides
    @JvmStatic
    fun provideDb(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "bootstrap.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}
