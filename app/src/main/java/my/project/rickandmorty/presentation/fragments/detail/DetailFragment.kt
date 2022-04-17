package my.project.rickandmorty.presentation.fragments.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import my.project.rickandmorty.R
import my.project.rickandmorty.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding:FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val character = args.character

        binding.apply {
            txtIdCharacter.text = character.id.toString()
            txtStatus.text = character.status
            Picasso.get().load(character.image).into(imgCharacter)
            txtName.text = character.name
            txtSpecie.text = character.species
            txtGender.text = character.gender
            txtNEpisodes.text = character.episode.size.toString()
            txtOrigin.text = character.origin.name
            txtLocation.text = character.location.name


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}