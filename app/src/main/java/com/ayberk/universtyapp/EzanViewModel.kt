package com.ayberk.universtyapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ayberk.universtyapp.models.AnkaraEzanSaati
import com.ayberk.universtyapp.models.ManisaEzanSaati
import com.ayberk.universtyapp.models.Sehir
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EzanViewModel@Inject constructor(private val repo: RetrofitRepository): ViewModel() {

    var ezanList : MutableLiveData<ManisaEzanSaati>
    var ankaraezan : MutableLiveData<AnkaraEzanSaati>


    init {
        ezanList = MutableLiveData()
        ankaraezan = MutableLiveData()

    }

    fun getObserverLiveData() : MutableLiveData<ManisaEzanSaati> {
        return ezanList
    }

    fun getObserverAnkaraLiveData() : MutableLiveData<AnkaraEzanSaati> {
        return ankaraezan
    }

    fun loadAnkaraData(page : String) {
        repo.getAnkaraezan(page,ankaraezan)
    }

    fun loadData(page : String) {
        repo.getEzan(page,ezanList)

    }

}
