package com.comcast.test.testapp.views.activities

import android.content.Context
import android.net.ConnectivityManager
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.comcast.test.testapp.R

open class BaseActivity : AppCompatActivity() {

    /**
     * Method that checks if the network is available or not
     * Return: true or false accordingly
     */
    val isNetworkConnectionAvailable: Boolean
        get() {
            var result = false
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = cm.activeNetworkInfo
            if (info != null) {
                if (info.isConnected) {
                    result = true
                }
            }
            return result
        }


    /**
     * Method that instantiates and launches the Fragment
     * @param fragment Fragment
     * @param tag Tag associated with the Fragment
     */
    protected fun launchFragment(fragment: Fragment, tag: String) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.add(R.id.container, fragment, tag)
        transaction.commitAllowingStateLoss()
    }
}
