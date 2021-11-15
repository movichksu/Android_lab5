package com.example.android_lab4.ui.form

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.android_lab4.R
import com.example.android_lab4.constants.Constants
import com.example.android_lab4.ui.dialog.DialogFragment
import com.example.android_lab4.ui.listener.PositiveClickListener
import com.example.android_lab4.ui.model.FieldType

class AddressFormActivity : AppCompatActivity(), PositiveClickListener {

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
            if (!validateFields()) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.error_toast),
                    Toast.LENGTH_LONG
                )
                    .show()
                return@setOnClickListener
            }
            finishIntentWithResult()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.form_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.cancel -> {
                DialogFragment().show(supportFragmentManager)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun finishIntentWithResult() {
        val addressFieldContent =
            "${countryEditText.text}, ${townEditText.text}, ${addressEditText.text}"
        val intent = Intent()
        intent.putExtras(bundleOf(Constants.RESULT_CONTENT to addressFieldContent))
        intent.putExtras(bundleOf(Constants.RESULT_TYPE to FieldType.ADDRESS_FIELD))
        setResult(RESULT_OK, intent)
        finish()
    }

    private fun validateFields(): Boolean {
        return when {
            countryEditText.text.isNullOrEmpty() -> false
            townEditText.text.isNullOrEmpty() -> false
            addressEditText.text.isNullOrEmpty() -> false
            else -> true
        }
    }

    override fun onPositive() {
        setResult(RESULT_CANCELED)
        finish()
    }
}