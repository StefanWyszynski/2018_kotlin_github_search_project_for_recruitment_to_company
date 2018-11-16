package com.assignment.search.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import com.assignment.GithubSearchApp
import com.assignment.GithubSearchMainActivity
import com.assignment.R
import com.assignment.di.ApplicationComponent
import com.assignment.search.repository.SearchResult
import com.assignment.search.view.adapter.IGithubSearchResultMarker
import com.assignment.search.view.adapter.OnItemClickListener
import com.assignment.search.view.adapter.UserListAdapter
import com.assignment.search.viewmodel.SearchViewModel
import com.assignment.search.viewmodel.SearchViewModelFactory
import com.assignment.utils.Shedulers
import com.jakewharton.rxbinding2.widget.RxTextView
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 *
 */
class SearchFragment : Fragment {

    val DOWNLOAD_DELAY_AFTER_TYPING_TEXT = 800L

    private lateinit var userListAdapter: UserListAdapter
    private lateinit var viewModel: SearchViewModel


    private lateinit var appComponent: ApplicationComponent

    @Inject
    lateinit var searchViewModelFactory: SearchViewModelFactory

    constructor() : super() {
        injectComponent()
    }

    private fun injectComponent() {
        appComponent = GithubSearchApp.getAppComponent()
        appComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        hideProgressBar()
        setupSearchResultList()
        setupSearchView()
    }

    private fun setupSearchView() {
        RxTextView.afterTextChangeEvents(searchEditText)
                .debounce(DOWNLOAD_DELAY_AFTER_TYPING_TEXT, TimeUnit.MILLISECONDS, Shedulers.computationThread())
                .observeOn(Shedulers.androidThread())
                .skip(1)
                .map { it.view().text.toString() }
                .subscribe { text ->
                    if (text.length > 0) {
                        searchGithub(text)
                    } else {
                        userListAdapter.setRepositiories(arrayListOf())
                    }
                }
    }

    private fun searchGithub(userOrRepositoryName: String) {
        showProgressBar()
        viewModel.searchForUserOrRepository(userOrRepositoryName)
    }

    private fun setupSearchResultList() {
        userList.layoutManager = LinearLayoutManager(context)
        userListAdapter = UserListAdapter()
        userList.adapter = userListAdapter
        userListAdapter.setOnClickListener(object : OnItemClickListener {
            override fun onListItemClick(item: IGithubSearchResultMarker) {
                showUserInfoFragment(item)
            }
        })
    }

    private fun showUserInfoFragment(item: IGithubSearchResultMarker) {
        (activity as GithubSearchMainActivity).putFragment(UserInfoFragment.newInstance(item.getUserName()), useBackStack = true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity!!.title = getString(R.string.fragment_search_title)
        bindViewModelData()
    }

    private fun bindViewModelData() {
        viewModel = ViewModelProviders.of(this, searchViewModelFactory).get(SearchViewModel::class.java)
        viewModel.getGithubSearchResultData().observe(this, Observer { downloadResult ->
            hideProgressBar()
            updateResultAdapter(downloadResult)
        })
    }

    private fun updateResultAdapter(result: SearchResult?) {
        result?.let {
            when (it.status) {
                SearchResult.ResultStatus.SUCCESS -> userListAdapter.setRepositiories(it.getResultAsSingleList())
                SearchResult.ResultStatus.FAILURE -> Toast.makeText(activity, "Error occured while downloading list", LENGTH_SHORT).show()
            }
        }
    }

    private fun showProgressBar() {
        progressbar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progressbar.visibility = View.GONE
    }
}
