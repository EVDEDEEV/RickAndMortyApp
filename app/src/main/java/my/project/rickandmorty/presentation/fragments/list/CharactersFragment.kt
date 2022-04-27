package my.project.rickandmorty.presentation.fragments.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import my.project.rickandmorty.data.models.CharacterModel
import my.project.rickandmorty.data.repository.Repository
import my.project.rickandmorty.databinding.FragmentCharactersBinding
import my.project.rickandmorty.presentation.viewModel.SharedViewModelFactory


typealias OnCharacterClicked = (CharacterModel) -> Unit

class CharactersFragment : Fragment() {

    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding
    private val sharedViewModel: SharedViewModel by activityViewModels {
        SharedViewModelFactory(
            Repository())
    }
    private val onClick: OnCharacterClicked = { character ->
        val action = CharactersFragmentDirections.actionListFragmentToDetailFragment(character)
        view?.findNavController()?.navigate(action)

    }

    private val adapter = CharacterAdapter(onClick)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        getCharactersFromViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            sharedViewModel.listCharacters.observe(viewLifecycleOwner) { response ->
                if (response.isSuccessful) {
                    response.body()?.let { body ->
                        adapter.setCharacters(body.results)
                    }
                } else {
                    Toast.makeText(context, "Data load error", Toast.LENGTH_SHORT).show()
                }
            }
            recyclerview.adapter = adapter
        }
    }

    private fun getCharactersFromViewModel() {
        val randomPage = (1..42).random()
        sharedViewModel.getCharacters(randomPage)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

