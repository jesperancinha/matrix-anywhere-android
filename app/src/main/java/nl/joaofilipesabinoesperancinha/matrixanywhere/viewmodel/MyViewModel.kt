package nl.joaofilipesabinoesperancinha.matrixanywhere.viewmodel

import androidx.lifecycle.ViewModel


class MyViewModel : ViewModel() {

    override fun onCleared() {
        super.onCleared()
        println("ViewModel cleared. All coroutines in viewModelScope are canceled.")
    }
}