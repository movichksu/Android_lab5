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
import com.example.android_lab4.constants.Constants.RESULT_CONTENT
import com.example.android_lab4.ui.dialog.AlternativeDialogFragment
import com.example.android_lab4.ui.dialog.listener.PositiveClickListener
import com.example.android_lab4.ui.model.FieldType

class CommentFormActivity : AppCompatActivity(), PositiveClickListener {

    private lateinit var commentEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var cancelButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_form)
        commentEditText = findViewById(R.id.commentEditText)
        saveButton = findViewById(R.id.saveButton)
        cancelButton = findViewById(R.id.cancelButton)

        cancelButton.setOnClickListener {
            onCancelPressed()
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
            val commentFieldContent = commentEditText.text.toString()
            val intent = Intent()
            intent.putExtras(bundleOf(RESULT_CONTENT to commentFieldContent))
            intent.putExtras(bundleOf(Constants.RESULT_TYPE to FieldType.COMMENT_FIELD))
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.form_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.cancel -> {
                onCancelPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onCancelPressed() {
        AlternativeDialogFragment
            .createInstance(
                getString(R.string.cancel_form),
                getString(R.string.are_you_sure_you_want_cancel)
            )
            .show(supportFragmentManager)
    }

    private fun validateFields(): Boolean {
        return when {
            commentEditText.text.isNullOrBlank() -> false
            else -> true
        }
    }

    override fun onPositive() {
        setResult(RESULT_CANCELED)
        finish()
    }
}