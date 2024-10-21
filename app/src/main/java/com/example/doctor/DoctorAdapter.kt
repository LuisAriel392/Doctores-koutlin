package com.example.doctor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class DoctorAdapter(
    private val doctors: List<Doctor>,
    private val onDoctorSelected: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_doctor, parent, false)
        return DoctorViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val doctor = doctors[position]
        holder.bind(doctor)
        holder.itemView.setOnClickListener {
            onDoctorSelected(doctor)
        }
    }

    override fun getItemCount(): Int = doctors.size

    class DoctorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val specialtyTextView: TextView = view.findViewById(R.id.specialtyTextView)
        private val availabilityTextView: TextView = view.findViewById(R.id.availabilityTextView)

        fun bind(doctor: Doctor) {
            nameTextView.text = doctor.name
            specialtyTextView.text = doctor.specialty
            availabilityTextView.text = doctor.availability
        }
    }
}
