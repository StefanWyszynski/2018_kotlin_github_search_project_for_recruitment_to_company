package com.assignment

import android.os.Bundle
import com.assignment.base.ActivityBase
import com.assignment.search.view.ui.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 *
 */
class GithubSearchMainActivity : ActivityBase() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(my_toolbar)
        putFragment(SearchFragment())
    }
}
