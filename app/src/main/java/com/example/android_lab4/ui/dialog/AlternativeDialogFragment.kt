package com.example.android_lab4.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.android_lab4.R
import com.example.android_lab4.constants.Constants.EMPTY_STRING
import com.example.android_lab4.ui.dialog.listener.PositiveClickListener

class AlternativeDialogFragment : DialogFragment() {

    private var listener: PositiveClickListener? = null
    private val title by lazy { arguments?.getString(FRAGMENT_TITLE_KEY) ?: EMPTY_STRING }
    private val message by lazy { arguments?.getString(FRAGMENT_MESSAGE_KEY) ?: EMPTY_STRING }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = (parentFragment ?: context) as? PositiveClickListener
    }

    override fun onDetach() {
        super.onDetach()

        listener = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.yes) { _, _ ->
                dismiss()
                listener?.onPositive()
            }
            .setNegativeButton(R.string.no) { _, _ ->
                dismiss()
            }
            .create()
    }

    fun show(manager: FragmentManager) {
        show(manager, DIALOG_FRAGMENT_TAG)
    }

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "DIALOG_FRAGMENT_TAG"
        private const val FRAGMENT_TITLE_KEY = "FRAGMENT_TITLE_KEY"
        private const val FRAGMENT_MESSAGE_KEY = "FRAGMENT_MESSAGE_KEY"

        fun createInstance(title: String, message: String): AlternativeDialogFragment =
            AlternativeDialogFragment().apply {
                arguments = bundleOf(
                    FRAGMENT_TITLE_KEY to title,
                    FRAGMENT_MESSAGE_KEY to message
                )
                isCancelable = false
            }
    }
}