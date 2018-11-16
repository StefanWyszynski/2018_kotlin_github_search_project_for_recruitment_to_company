package com.assignment.base

import android.arch.lifecycle.MutableLiveData

/*
 * Copyright 2018, Assignment/test project for some company
 *
 * @author Stefan Wyszynski
 */
abstract class RepositoryBase<T> {

    var liveData: MutableLiveData<T>

    init {
        liveData = MutableLiveData()
    }

    open fun setValue(data: T) {
        liveData.postValue(data)
    }
}
