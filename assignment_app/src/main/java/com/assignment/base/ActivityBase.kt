package com.assignment.base

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

import com.assignment.R

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
abstract class ActivityBase : AppCompatActivity() {

    fun putFragment(fragment: Fragment,
                    useBackStack: Boolean = false,
                    useAnimation: Boolean = true,
                    animationIn: Int = R.anim.fade_in,
                    animationOut: Int = R.anim.fade_out) {
        val supportFragmentManager = supportFragmentManager
        val findedFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (useAnimation) {
            fragmentTransaction.setCustomAnimations(animationIn, animationOut, animationIn, animationOut)
        }
        if (findedFragment == null) {
            fragmentTransaction.add(R.id.fragment_container, fragment)
        } else {
            fragmentTransaction.replace(R.id.fragment_container, fragment)
        }

        if (useBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }
}
