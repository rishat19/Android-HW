package com.itis.ganiev.baseproject

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.DialogFragment
import java.lang.NumberFormatException

class DialogWindow : DialogFragment() {

    lateinit var listener : (name: String, capital: String, position: Int) -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = requireActivity().layoutInflater
        val view = inflater.inflate(R.layout.fragment_dialog_window,null)
        builder.setView(view)
            .setTitle("Новая страна")
            .setPositiveButton("ОК") { _: DialogInterface, _: Int ->
                val name: String = view.findViewById<AppCompatEditText>(R.id.dialog_et_name).text.toString()
                val capital: String = view.findViewById<AppCompatEditText>(R.id.dialog_et_capital).text.toString()
                val position: Int =
                    try {
                        view.findViewById<AppCompatEditText>(R.id.dialog_et_position).text.toString().toInt() - 1
                    }
                    catch (e: NumberFormatException) {
                        -1
                    }
                if (name.isNotEmpty() && capital.isNotEmpty()) {
                    listener.invoke(name, capital, position)
                } else {
                    Toast.makeText(this.context, "Первые два поля должны быть заполнены!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("ОТМЕНА", null)
        return builder.create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {}

}