package com.watkins.lisa

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.watkins.lisa.util.ConnectivityLiveData
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var connectivityManager: ConnectivityManager

    override fun supportFragmentInjector() = dispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupConnectivityManager()
    }

    /**
     * If we lose network connection, show the user a "hey, there's no network" message for 4 seconds and then hide it.
     */
    private fun setupConnectivityManager() {
        val connectionStatusBar = findViewById<TextView>(R.id.connection_status)
        val connectivityLiveData = ConnectivityLiveData(connectivityManager)

        connectivityLiveData.observe(this, Observer { network ->
            connectionStatusBar.apply {
                if (network) {
                    visibility = View.GONE
                } else {
                    visibility = View.VISIBLE
                    postDelayed({
                        visibility = View.GONE
                    }, 4000)
                }
            }
        })
    }
}
