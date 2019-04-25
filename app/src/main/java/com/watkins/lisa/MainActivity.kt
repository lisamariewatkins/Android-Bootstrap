package com.watkins.lisa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.watkins.lisa.util.ConnectivityLiveData
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var connectivityLiveData: ConnectivityLiveData

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        observeNetworkConnectivity()
    }

    /**
     * If we lose network connection, show the user a "hey, there's no network" message for a few seconds and then hide it.
     */
    private fun observeNetworkConnectivity() {
        connectivityLiveData.observe(this, Observer { connected ->
            if (!connected) Snackbar.make(findViewById(R.id.main_activity), R.string.network_connectivity, Snackbar.LENGTH_LONG).show()
        })
    }
}
