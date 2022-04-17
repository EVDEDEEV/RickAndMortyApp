package my.project.rickandmorty.presentation.fragments.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import my.project.rickandmorty.data.repository.Repository
import my.project.rickandmorty.databinding.FragmentListBinding
import my.project.rickandmorty.presentation.viewModel.SharedViewModel
import my.project.rickandmorty.presentation.viewModel.SharedViewModelFactory

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory(
            Repository())
    }
    private var adapter = CharacterAdapter()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        getCharactersFromViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
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

                } else {
                    Toast.makeText(context, "Data load error", Toast.LENGTH_SHORT).show()
                }
            }
            recyclerview.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
//            recyclerview.layoutManager = LinearLayoutManager(context)
            recyclerview.adapter = adapter
//            StaggeredGridLayoutManager
        }
    }


    private fun getCharactersFromViewModel() {
        sharedViewModel.getCharacters(1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

