package com.example.doctor

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.doctor.R.id.doctorSpecialtyTextView
import java.util.*

class AppointmentDetailsFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    @SuppressLint("MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_appointment_details, container, false)

        // Inicializar el ViewModel compartido
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)

        // Mostrar el doctor seleccionado
        val doctorNameTextView: TextView = view.findViewById(R.id.doctorNameTextView)
        val doctorSpecialtyTextView: TextView = view.findViewById(doctorSpecialtyTextView)
        sharedViewModel.selectedDoctor.observe(viewLifecycleOwner) { doctor ->
            doctorNameTextView.text = doctor.name
            doctorSpecialtyTextView.text = doctor.specialty
        }

        // Configurar el DatePicker y TimePicker
        val datePicker: DatePicker = view.findViewById(R.id.datePicker)
        val timePicker: TimePicker = view.findViewById(R.id.timePicker)
        timePicker.setIs24HourView(true) // Establecer el formato de 24 horas

        view.findViewById<View>(R.id.nextButton).setOnClickListener {
            // Obtener la fecha seleccionada del DatePicker
            val day = datePicker.dayOfMonth
            val month = datePicker.month
            val year = datePicker.year

            // Obtener la hora seleccionada del TimePicker (disponible a partir de API 23)
            val hour = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) timePicker.hour else timePicker.currentHour
            val minute = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) timePicker.minute else timePicker.currentMinute

            // Crear un objeto Calendar con la fecha y hora seleccionadas
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day, hour, minute)

            // Guardar la fecha y hora seleccionada en el ViewModel compartido
            sharedViewModel.selectDate(calendar.time)

            // Navegar al ConfirmationFragment
            (activity as MainActivity).replaceFragment(ConfirmationFragment())
        }

        return view
    }
}
