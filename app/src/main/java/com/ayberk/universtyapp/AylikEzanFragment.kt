package com.ayberk.universtyapp

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayberk.universtyapp.databinding.FragmentEzanSaatleriBinding
import com.ayberk.universtyapp.models.ManisaEzanSaati
import com.ayberk.universtyapp.models.ManisaEzanSaatiItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class AylikEzanFragment : Fragment() {

    private var _binding: FragmentEzanSaatleriBinding? = null
    private val binding get() = _binding!!
    private lateinit var aylikezanAdapter: aylikezanAdapter

    lateinit var resultList: ManisaEzanSaatiItem
    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(EzanViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEzanSaatleriBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.rcylerEzan.layoutManager = LinearLayoutManager(requireContext())


        initRecyclerViews()
        fetchEzan()
        viewModel.getObserverLiveData().observe(viewLifecycleOwner, object :
            Observer<ManisaEzanSaati> {
            override fun onChanged(t: ManisaEzanSaati?) {

                if (t != null) {
                    aylikezanAdapter.setLists(t)
                    arguments?.let {
                        //    val ezangun = EzanSaatleriFragmentArgs.fromBundle(it).pozisyon

                    }

                }
            }

        })
        return view
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu,menu)
        super.onCreateOptionsMenu(menu, inflater)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {


        when(item.itemId){
            R.id.anasayfa -> {
                findNavController().navigate(R.id.sehirlerFragment)
                Toast.makeText(requireContext(),"Anasayfa", Toast.LENGTH_SHORT).show()
            }
            R.id.geri ->
                findNavController().navigate(R.id.ezanSaatleriFragment)
        }
        return super.onOptionsItemSelected(item)
    }



    fun initRecyclerViews() {

        aylikezanAdapter = aylikezanAdapter()
        binding.rcylerEzan.adapter = aylikezanAdapter

    }



    fun fetchEzan() {

        CoroutineScope(Dispatchers.IO).launch {

            val job1: Deferred<Unit> = async {
                viewModel.loadData("9716")
            }
            job1.await()
        }
    }

}