package com.ayberk.universtyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ayberk.universtyapp.databinding.FragmentSehirlerBinding
import com.ayberk.universtyapp.models.Sehir
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SehirlerFragment : Fragment() {

    private var _binding: FragmentSehirlerBinding? = null
    private val binding get() = _binding!!
    private lateinit var universiteAdapter: SehirAdapter

    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(viewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSehirlerBinding.inflate(inflater, container, false)
        val view = binding.root
        initRecyclerViews()
        val rcyler = view.findViewById<RecyclerView>(R.id.recyclerSehir)
        fetchCharacters()
        rcyler?.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getObserverLiveData().observe(viewLifecycleOwner, object : Observer<Sehir> {
            override fun onChanged(t: Sehir?) {

                if (t != null) {
                    universiteAdapter.setLists(t)
                }
            }

        })
        return view
    }


    fun initRecyclerViews() {

        universiteAdapter = SehirAdapter()
       binding.recyclerSehir.adapter = universiteAdapter

    }

    fun fetchCharacters() {

        CoroutineScope(Dispatchers.IO).launch {

            val job1: Deferred<Unit> = async {
                viewModel.loadData("2")
            }
            job1.await()
        }
    }
}