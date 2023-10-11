package com.gramirez.intent3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondaryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_num)

        // Obtener referencia al TextView
        val valorPasadoTextView = findViewById<TextView>(R.id.valorPasado)

        // Obtener el valor actual desde el Intent
        var valorPasado = intent.getIntExtra("valor_modificado", 0)
        valorPasado++
        // Actualizar el TextView con el valor actualizado
        valorPasadoTextView.text = (valorPasado).toString()

        val botonVolver = findViewById<Button>(R.id.volver)
        botonVolver.setOnClickListener {
            // Devolver el valor actualizado a MainActivity
            val intentResultado = Intent()
            intentResultado.putExtra("valor_modificado", valorPasado)
            setResult(Activity.RESULT_OK, intentResultado)
            finish()
        }
    }


}
