package com.example.rickandmorty

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.rickandmorty.data.CharacterRepository
import com.example.rickandmorty.screen.MainFragment

class MainActivity : AppCompatActivity() {
    val repository = CharacterRepository()

    private val navController get() = findNavController(R.id.fragmentContainer)

    override fun onCreate(savedInstanceState: Bundle?) {
        //println("hello")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    /*fun openDetails(loanId: Long) {
        val action = MainFragmentDirections.actionHistoryFragmentToDetailsFragment(loanId)
        navController.navigate(action)
    }*/
}