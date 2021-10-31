package com.example.android_lab4.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import com.example.android_lab4.R
import com.example.android_lab4.constants.Constants.RESULT
import com.example.android_lab4.ui.model.FieldContent
import com.example.android_lab4.ui.model.FieldType
import com.google.android.material.textfield.TextInputLayout
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var nameEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var commentEditText: EditText
    private lateinit var nameInputLayout: TextInputLayout
    private lateinit var addressInputLayout: TextInputLayout
    private lateinit var commentInputLayout: TextInputLayout

    private val someActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (result.resultCode) {
            RESULT_OK -> {
                val data = result.data?.getStringExtra(RESULT)
                if (data != null) {
                    val fieldContent = Json.decodeFromString(data) as FieldContent
                    when (fieldContent.fieldType) {
                        FieldType.NAME_FIELD -> nameEditText.setText(fieldContent.content)
                        FieldType.ADDRESS_FIELD -> addressEditText.setText(fieldContent.content)
                        FieldType.COMMENT_FIELD -> commentEditText.setText(fieldContent.content)
                    }
                } else throw Exception()
            }
            RESULT_CANCELED -> { }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nameEditText = findViewById(R.id.userNameEditText)
        addressEditText = findViewById(R.id.addressEditText)
        commentEditText = findViewById(R.id.commentEditText)
        nameInputLayout = findViewById(R.id.userNameInputLayout)
        addressInputLayout = findViewById(R.id.addressInputLayout)
        commentInputLayout = findViewById(R.id.commentInputLayout)

        nameInputLayout.setEndIconOnClickListener {
            launchActivity(UserNameFormActivity::class.java)
        }
        addressInputLayout.setEndIconOnClickListener {
            launchActivity(AddressFormActivity::class.java)
        }
        commentInputLayout.setEndIconOnClickListener {
            launchActivity(CommentFormActivity::class.java)
        }
    }

    private fun launchActivity(activityClass: Class<out Activity>) {
        val intent = Intent(this@MainActivity, activityClass)
        someActivityResultLauncher.launch(intent)
    }
}