package com.example.android_lab4.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.android_lab4.R
import com.example.android_lab4.constants.Constants
import com.example.android_lab4.ui.model.FieldContent
import com.example.android_lab4.ui.model.FieldType
import com.google.android.material.textfield.TextInputLayout
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class UserNameFormActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_form)
        nameEditText = findViewById(R.id.nameEditText)
        surnameEditText = findViewById(R.id.surnameEditText)
        saveButton = findViewById(R.id.saveButton)
        cancelButton = findViewById(R.id.cancelButton)

        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        saveButton.setOnClickListener {
            val nameFieldContent =
                FieldContent(FieldType.NAME_FIELD, "${nameEditText.text} ${surnameEditText.text}")
            val serializedName = Json.encodeToString(nameFieldContent)
            val intent = Intent()
            intent.putExtras(bundleOf(Constants.RESULT to serializedName))
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}