package my.project.rickandmorty.presentation.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import my.project.rickandmorty.data.models.CharacterModel
import my.project.rickandmorty.databinding.ItemListBinding

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var listCharacters = emptyList<CharacterModel>()

    class CharacterViewHolder(val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharacterModel) {
            binding.txtIdCharacter.text = character.id.toString()
            binding.txtNameCharacter.text = character.name
            Picasso.get().load(character.image).into(binding.characterImg)
            binding.txtStatus.text = character.status

            itemView.setOnClickListener { view ->
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(character)
                view.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemListBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(listCharacters[position])
    }

    override fun getItemCount(): Int {
        return listCharacters.size
    }

    fun setCharacters(characters: List<CharacterModel>) {
        listCharacters = characters
        notifyDataSetChanged()
    }
}