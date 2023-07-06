package com.example.rickandmorty.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
//import androidx.navigation.fragment.navArgs
import com.example.rickandmorty.R
import com.example.rickandmorty.data.Character
import com.example.rickandmorty.databinding.FragmentDetailsBinding
import com.example.rickandmorty.mainActivity

import kotlinx.coroutines.launch

class DetailsFragment : Fragment() {

	private var _binding: FragmentDetailsBinding? = null
	// This property is only valid between onCreateView and onDestroyView.
	private val binding get() = _binding!!

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		_binding = FragmentDetailsBinding.inflate(inflater, container, false)
		return binding.root
	}

	//private val args: DetailsFragmentArgs by navArgs()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		//launchLoanDetailsLoading()
	}

	/*private fun launchLoanDetailsLoading() {
		showProgress()

		lifecycleScope.launch {
			try {
				//val id = args.loanId
				val repository = mainActivity.repository
				//val loan = repository.getLoanById(id)
				showContent(loan)
			} catch (ex: Exception) {
				showError(ex.message.orEmpty())
			}
		}
	}*/

	private fun showContent(character: Character) {
		with(binding) {
			progressBar.isVisible = false
			errorContent.isVisible = false

			detailsContent.isVisible = true

			//percent.text = getString(R.string.percentTemplate, character.percent)
			//period.text = getString(R.string.periodTemplate, character.period)
			//status.text = formatLoanStatus(requireContext(), character.status)
			//lastName.text = character.lastName
			//firstName.text = character.firstName
			//phone.text = character.phoneNumber
		}
	}

	private fun showProgress() {
		with(binding) {
			errorContent.isVisible = false
			detailsContent.isVisible = false

			progressBar.isVisible = true
		}
	}

	private fun showError(message: String) {
		with(binding) {
			progressBar.isVisible = false
			detailsContent.isVisible = false

			errorContent.isVisible = true
			errorText.text = message
			//errorButton.setOnClickListener { launchLoanDetailsLoading() }
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}