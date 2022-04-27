package my.project.rickandmorty.presentation.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import my.project.rickandmorty.data.models.CharacterModel
import my.project.rickandmorty.databinding.ItemCharacterBinding

class CharacterAdapter(val onClick: OnCharacterClicked) :
    RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    private var listCharacters = emptyList<CharacterModel>()

    class CharacterViewHolder(
        private val binding: ItemCharacterBinding,
        val onClick: OnCharacterClicked,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: CharacterModel) {
            binding.txtIdCharacter.text = character.id.toString()
            binding.textNameCharacter.text = character.name
            Picasso.get().load(character.image).into(binding.characterImg)
            binding.textStatus.text = character.status
            itemView.setOnClickListener { view ->
                onClick.invoke(character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(layoutInflater, parent, false)
        return CharacterViewHolder(binding, onClick)
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