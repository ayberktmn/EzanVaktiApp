package com.ayberk.universtyapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayberk.universtyapp.models.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class viewModel@Inject constructor(private val repo: RetrofitRepository): ViewModel() {

    var uniList : MutableLiveData<Sehir>


    init {
        uniList = MutableLiveData()

    }

    fun getObserverLiveData() : MutableLiveData<Sehir> {
        return uniList
    }


    fun loadData(page : String) {
        repo.getSehirler(page,uniList)
    }

}