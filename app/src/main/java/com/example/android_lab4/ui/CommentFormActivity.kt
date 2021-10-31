package com.example.android_lab4.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.android_lab4.R
import com.example.android_lab4.constants.Constants.RESULT
import com.example.android_lab4.ui.model.FieldContent
import com.example.android_lab4.ui.model.FieldType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class CommentFormActivity : AppCompatActivity() {

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
            setResult(RESULT_CANCELED)
            finish()
        }

        saveButton.setOnClickListener {
            val commentFieldContent =
                FieldContent(FieldType.COMMENT_FIELD, commentEditText.text.toString())
            val serializedComment = Json.encodeToString(commentFieldContent)
            val intent = Intent()
            intent.putExtras(bundleOf(RESULT to serializedComment))
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}