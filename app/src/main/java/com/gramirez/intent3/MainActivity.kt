package com.gramirez.intent3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obtener referencias a los TextView
        var valorUnoTextView = findViewById<TextView>(R.id.valorUno)
        var valorDosTextView = findViewById<TextView>(R.id.valorDos)
        var valorTresTextView = findViewById<TextView>(R.id.valorTres)
        var valorCuatroTextView = findViewById<TextView>(R.id.valorCuatro)


        var botonUno = findViewById<Button>(R.id.uno)
        var botonDos = findViewById<Button>(R.id.dos)
        var botonTres = findViewById<Button>(R.id.tres)
        var botonCuatro = findViewById<Button>(R.id.cuatro)

        botonUno.setOnClickListener {
            callActivity(1)
        }

        botonDos.setOnClickListener {
            callActivity(2)
        }

        botonTres.setOnClickListener {
            callActivity(3)
        }

        botonCuatro.setOnClickListener {
            callActivity(4)
        }

    }

    private fun callActivity(numeroBoton: Int) {
        val valorContador = obtenerValorBoton(numeroBoton)
        val intent = Intent(this, SecondaryActivity::class.java).apply {
            putExtra("boton", numeroBoton)
            putExtra("valor_modificado", valorContador) // Pasa el valor actualizado
        }
        startActivityForResult(intent, numeroBoton)// Usar numeroBoton como requestCode
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val valorModificado = data?.getIntExtra("valor_modificado", 0)

            if (valorModificado != null) {
                // Usa requestCode para determinar qué TextView actualizar
                when (requestCode) {
                    1 -> actualizarValorBoton(1, valorModificado)
                    2 -> actualizarValorBoton(2, valorModificado)
                    3 -> actualizarValorBoton(3, valorModificado)
                    4 -> actualizarValorBoton(4, valorModificado)
                }
            }
        }
    }


    private fun obtenerValorBoton(numeroBoton: Int): Int {
        var textViewId = when (numeroBoton) {
            1 -> R.id.valorUno
            2 -> R.id.valorDos
            3 -> R.id.valorTres
            4 -> R.id.valorCuatro
            else -> 0 // Manejar caso de número de botón inválido
        }

        var textView = findViewById<TextView>(textViewId)
        return textView.text.toString().toIntOrNull() ?: 0
    }

    private fun actualizarValorBoton(numeroBoton: Int, valorModificado: Int) {
        var textViewId = when (numeroBoton) {
            1 -> R.id.valorUno
            2 -> R.id.valorDos
            3 -> R.id.valorTres
            4 -> R.id.valorCuatro
            else -> 0 // Manejar caso de número de botón inválido
        }

        var textView = findViewById<TextView>(textViewId)
        textView.text = valorModificado.toString()
    }
}