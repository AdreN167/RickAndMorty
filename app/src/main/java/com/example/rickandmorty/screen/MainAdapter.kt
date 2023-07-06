package com.example.rickandmorty.screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.databinding.ItemCharacterBinding
import com.example.rickandmorty.data.Character
import com.squareup.picasso.Picasso

class MainAdapter(private val characterClickListener: (Character) -> Unit) : RecyclerView.Adapter<MainViewHolder>() {

	var characters: List<Character> = emptyList()
		set(value) {
			field = value
			notifyDataSetChanged()
		}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		val binding = ItemCharacterBinding.inflate(inflater, parent, false)
		return MainViewHolder(binding)
	}

	override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
		holder.bind(characters[position], characterClickListener)
	}

	override fun getItemCount(): Int =
		characters.size
}

class MainViewHolder(private val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root) {

	fun bind(character: Character, characterClickListener: (Character) -> Unit) {
		with(binding) {
			Picasso.get().load(character.image).into(imageCharacterView)
			nameCharacterView.text = character.name
		}

		itemView.setOnClickListener {
			characterClickListener(character)
		}
	}
}