package com.example.android_lab4.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import com.example.android_lab4.R
import com.example.android_lab4.constants.Constants.RESULT_CONTENT
import com.example.android_lab4.constants.Constants.RESULT_TYPE
import com.example.android_lab4.ui.dialog.AlternativeDialogFragment
import com.example.android_lab4.ui.form.AddressFormActivity
import com.example.android_lab4.ui.form.CommentFormActivity
import com.example.android_lab4.ui.form.UserNameFormActivity
import com.example.android_lab4.ui.dialog.listener.PositiveClickListener
import com.example.android_lab4.ui.model.FieldType
import com.google.android.material.textfield.TextInputLayout
import java.lang.Exception

class MainActivity : AppCompatActivity(), PositiveClickListener {

    private lateinit var nameEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var commentEditText: EditText
    private lateinit var nameInputLayout: TextInputLayout
    private lateinit var addressInputLayout: TextInputLayout
    private lateinit var commentInputLayout: TextInputLayout

    private val someActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val fieldType = result.data?.getSerializableExtra(RESULT_TYPE)
            val fieldContent = result.data?.getStringExtra(RESULT_CONTENT)
            if (fieldType != null) {
                when (fieldType) {
                    FieldType.NAME_FIELD -> nameEditText.setText(fieldContent)
                    FieldType.ADDRESS_FIELD -> addressEditText.setText(fieldContent)
                    FieldType.COMMENT_FIELD -> commentEditText.setText(fieldContent)
                }
            } else throw Exception()
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.exit -> {
                AlternativeDialogFragment
                    .createInstance(
                        getString(R.string.close_app),
                        getString(R.string.are_you_sure_you_want_exit)
                    )
                    .show(supportFragmentManager)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun launchActivity(activityClass: Class<out Activity>) {
        val intent = Intent(this@MainActivity, activityClass)
        someActivityResultLauncher.launch(intent)
    }

    override fun onPositive() {
        finish()
    }
}