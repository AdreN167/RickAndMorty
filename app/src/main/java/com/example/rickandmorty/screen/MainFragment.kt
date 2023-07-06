package com.example.rickandmorty.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.rickandmorty.data.Character
import com.example.rickandmorty.databinding.FragmentMainBinding
import com.example.rickandmorty.mainActivity
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

	private var _binding: FragmentMainBinding? = null
	// This property is only valid between onCreateView and onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		println("Hello1")
		_binding = FragmentMainBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		println("Hello2")
		super.onViewCreated(view, savedInstanceState)

		binding.mainList.adapter = MainAdapter(::handleCharacterClick)

		launchMainLoading()
	}

	private fun handleCharacterClick(character: Character) {
		//mainActivity.openDetails(loanId = character.id)
	}

	private fun launchMainLoading() {
		showProgress()

		lifecycleScope.launch {
			try {

				val repository = mainActivity.repository
				val characters = repository.getAllCharacters()
				print(characters)
				showContent(characters)
			} catch (ex: Exception) {
				showError(ex.message.orEmpty())
			}
		}
	}

	private fun showContent(characters: List<Character>) {

		with(binding) {
			progressBar.isVisible = false
			errorContent.isVisible = false

			mainContent.isVisible = true
			(mainList.adapter as? MainAdapter)?.characters = characters
		}
	}

	private fun showProgress() {
		with(binding) {
			errorContent.isVisible = false
			mainContent.isVisible = false

			progressBar.isVisible = true
		}
	}

	private fun showError(message: String) {
		with(binding) {
			progressBar.isVisible = false
			mainContent.isVisible = false

			errorContent.isVisible = true
			errorText.text = message
			errorButton.setOnClickListener { launchMainLoading() }
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		binding.mainList.adapter = null
		_binding = null
	}
}