package com.ayberk.universtyapp

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayberk.universtyapp.databinding.FragmentAnkaraEzanBinding
import com.ayberk.universtyapp.databinding.FragmentEzanSaatleriBinding
import com.ayberk.universtyapp.models.AnkaraEzanSaati
import com.ayberk.universtyapp.models.ManisaEzanSaati
import com.ayberk.universtyapp.models.ManisaEzanSaatiItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class AnkaraEzanFragment : Fragment() {

    private var _binding: FragmentAnkaraEzanBinding? = null
    private val binding get() = _binding!!
    private lateinit var ankaraEzanAdapter: AnkaraEzanAdapter

    val viewModel by lazy {
        ViewModelProvider(this, defaultViewModelProviderFactory).get(EzanViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAnkaraEzanBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.rcylerAnkara.layoutManager = LinearLayoutManager(requireContext())
        setHasOptionsMenu(true)

        initRecyclerViews()
        fetchEzan()
        viewModel.getObserverAnkaraLiveData().observe(viewLifecycleOwner, object : Observer<AnkaraEzanSaati> {
            override fun onChanged(t: AnkaraEzanSaati?) {

                if (t != null) {
                    ankaraEzanAdapter.setLists(t)
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
                findNavController().navigate(R.id.sehirlerFragment)
        }
        return super.onOptionsItemSelected(item)

    }

    fun initRecyclerViews() {

        ankaraEzanAdapter = AnkaraEzanAdapter()
        binding.rcylerAnkara.adapter = ankaraEzanAdapter

    }

    fun fetchEzan() {

        CoroutineScope(Dispatchers.IO).launch {

            val job1: Deferred<Unit> = async {
                viewModel.loadAnkaraData("9206")
            }
            job1.await()
        }
    }

}