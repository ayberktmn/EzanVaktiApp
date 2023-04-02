package com.ayberk.universtyapp

import androidx.lifecycle.MutableLiveData
import com.ayberk.universtyapp.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetrofitRepository @Inject constructor(private val retrofitServiceInstance: RetrofitInstance) {

    fun getAnkaraezan(page: String, liveData: MutableLiveData<AnkaraEzanSaati>){
        retrofitServiceInstance.getAnkaraEzan(page).enqueue(object : Callback<AnkaraEzanSaati> {
            override fun onResponse(call: Call<AnkaraEzanSaati>, response: Response<AnkaraEzanSaati>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<AnkaraEzanSaati>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }

    fun getSehirler(page: String, liveData: MutableLiveData<Sehir>){
        retrofitServiceInstance.getSehir(page).enqueue(object : Callback<Sehir> {
            override fun onResponse(call: Call<Sehir>, response: Response<Sehir>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<Sehir>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
    fun getEzan(page: String, liveData: MutableLiveData<ManisaEzanSaati>){
        retrofitServiceInstance.getEzan(page).enqueue(object : Callback<ManisaEzanSaati> {
            override fun onResponse(call: Call<ManisaEzanSaati>, response: Response<ManisaEzanSaati>) {
                liveData.postValue(response.body())
            }

            override fun onFailure(call: Call<ManisaEzanSaati>, t: Throwable) {
                liveData.postValue(null)
            }

        })
    }
}