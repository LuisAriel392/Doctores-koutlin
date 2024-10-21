package com.example.doctor

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class ConfirmationFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_confirmation, container, false)

        // Inicializar el ViewModel compartido
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Mostrar los detalles de la cita
        val doctorNameTextView: TextView = view.findViewById(R.id.doctorNameTextView)
        val doctorSpecialtyTextView: TextView = view.findViewById(R.id.doctorSpecialtyTextView)
        val appointmentDateTextView: TextView = view.findViewById(R.id.appointmentDateTextView)

        sharedViewModel.selectedDoctor.observe(viewLifecycleOwner) { doctor ->
            doctorNameTextView.text = doctor.name
            doctorSpecialtyTextView.text = doctor.specialty
        }

        sharedViewModel.selectedDate.observe(viewLifecycleOwner) { date ->
            appointmentDateTextView.text = date.toString() // Puedes formatear la fecha a un formato más legible
        }

        // Botón de confirmación
        view.findViewById<View>(R.id.confirmButton).setOnClickListener {
            Toast.makeText(requireContext(), "Cita confirmada", Toast.LENGTH_SHORT).show()
        }

        return view
    }
}
