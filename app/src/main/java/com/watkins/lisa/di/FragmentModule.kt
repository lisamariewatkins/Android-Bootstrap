package com.watkins.lisa.di


import com.watkins.lisa.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Any fragment that needs to be injected should be provided here.
 *
 * This module is references by MainActivity's AndroidInjector. The providers for these fragments are initialized when MainActivity's
 * component (i.e. subcomponent) is initialized.
 */
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributesHomeFragment(): HomeFragment
}
