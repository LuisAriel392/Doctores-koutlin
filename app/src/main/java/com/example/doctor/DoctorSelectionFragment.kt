package com.example.doctor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.doctor.R.id.recyclerView

class DoctorSelectionFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_doctor_selection, container, false)

        // Inicializar el ViewModel compartido
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Configurar el RecyclerView con la lista de doctores
        val recyclerView: RecyclerView = view.findViewById(recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val doctors = listOf(
            Doctor("Dr. Pérez", "Cardiología", "Disponible"),
            Doctor("Dra. García", "Dermatología", "Disponible"),
            Doctor("Dr. Gómez", "Neurología", "Ocupado")
        )

        val adapter = DoctorAdapter(doctors) { doctor ->
            sharedViewModel.selectDoctor(doctor)
            // Navegar al siguiente fragment
            (activity as MainActivity).replaceFragment(AppointmentDetailsFragment())
        }
        recyclerView.adapter = adapter

        return view
    }
}
