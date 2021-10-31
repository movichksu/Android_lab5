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
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AddressFormActivity : AppCompatActivity() {

    private lateinit var countryEditText: EditText
    private lateinit var townEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address_form)
        countryEditText = findViewById(R.id.countryEditText)
        addressEditText = findViewById(R.id.addressEditText)
        townEditText = findViewById(R.id.townEditText)
        saveButton = findViewById(R.id.saveButton)
        cancelButton = findViewById(R.id.cancelButton)

        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }

        saveButton.setOnClickListener {
            val addressFieldContent =
                FieldContent(
                    FieldType.ADDRESS_FIELD,
                    "${countryEditText.text}, ${townEditText.text}, ${addressEditText.text}"
                )
            val serializedAddress = Json.encodeToString(addressFieldContent)
            val intent = Intent()
            intent.putExtras(bundleOf(Constants.RESULT to serializedAddress))
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}