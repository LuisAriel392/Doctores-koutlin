package com.example.doctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class SharedViewModel : ViewModel() {

    // Datos del doctor seleccionado
    private val _selectedDoctor = MutableLiveData<Doctor>()
    val selectedDoctor: LiveData<Doctor> get() = _selectedDoctor

    // Datos de la fecha y hora de la cita
    private val _selectedDate = MutableLiveData<Date>()
    val selectedDate: LiveData<Date> get() = _selectedDate

    fun selectDoctor(doctor: Doctor) {
        _selectedDoctor.value = doctor
    }

    fun selectDate(date: Date) {
        _selectedDate.value = date
    }
}

data class Doctor(
    val name: String,
    val specialty: String,
    val availability: String
)
