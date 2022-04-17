package my.project.rickandmorty.presentation.fragments.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import my.project.rickandmorty.R
import my.project.rickandmorty.data.repository.Repository
import my.project.rickandmorty.databinding.FragmentListBinding
import my.project.rickandmorty.presentation.viewModel.SharedViewModel
import my.project.rickandmorty.presentation.viewModel.SharedViewModelFactory

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels{ SharedViewModelFactory(
        Repository()) }
    private var adapter = CharacterAdapter()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        getCharactersFromViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            sharedViewModel.listCharacters.observe(viewLifecycleOwner) { response ->
                if (response.isSuccessful) {
                    adapter.setCharacters(response.body()!!.results)
//                Log.d("Result", response.body()!!.results.toString())
//                Toast.makeText(context, "Data load success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Data load error", Toast.LENGTH_SHORT).show()
//                Log.d("ResultError", response.code().toString())
                }
            }

            recycclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            recycclerview.adapter = adapter

//            btnFilter.setOnClickListener {
//                findNavController().navigate(R.id.action_listFragment_to_filterFragment)
//            }

            sharedViewModel.isFilter.observe(viewLifecycleOwner) {
                titleActionReset.visibility = if (it) View.VISIBLE else View.INVISIBLE

            }
            titleActionReset.setOnClickListener {
                getCharactersFromViewModel()
                sharedViewModel.filterValue.value = arrayOf(0,0)
            }

        }

//        getNameSearchView()

//        binding.btnFilter.setOnClickListener {
//            findNavController().navigate(R.id.action_listFragment_to_filterFragment)
//        }

//        binding.details.setOnClickListener {
//            findNavController().navigate(R.id.action_listFragment_to_detailFragment)
//        }

    }

//    private fun getNameSearchView() {
//        binding.apply {
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
//                androidx.appcompat.widget.SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    sharedViewModel.getByName(query.toString())
//                    searchView.setQuery("", false)
//                    searchView.clearFocus()
//                    return false
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    return false
//                }
//            })
//        }
//    }

    private fun getCharactersFromViewModel() {
        sharedViewModel.getCharacters(1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

