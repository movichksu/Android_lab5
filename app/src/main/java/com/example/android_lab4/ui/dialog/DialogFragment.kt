package com.example.android_lab4.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.android_lab4.R
import com.example.android_lab4.ui.listener.PositiveClickListener

class DialogFragment : DialogFragment() {
    private var listener: PositiveClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        listener = (parentFragment ?: context) as? PositiveClickListener
    }

    override fun onDetach() {
        super.onDetach()

        listener = null
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val title = "Close app"
        val message = "Are you sure you want to close the app?"

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
    }
}