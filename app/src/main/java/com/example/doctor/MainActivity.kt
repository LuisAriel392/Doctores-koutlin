package com.example.doctor


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar la barra de herramientas (Toolbar)
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Reserva de Citas Médicas"

        // Inicializar el ViewModel compartido
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)

        // Iniciar con el primer fragment si no hay un estado guardado
        if (savedInstanceState == null) {
            replaceFragment(DoctorSelectionFragment())
        }
    }

    // Método para reemplazar fragments
    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
