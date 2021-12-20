package ru.intelligency.scholarship.presentation.ui.portfolio.fragments

import android.os.Bundle
import android.view.View
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentDocumentDetailsEditBinding
import ru.intelligency.scholarship.presentation.base.BaseFragment

class DocumentDetailsEditFragment : BaseFragment<FragmentDocumentDetailsEditBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_document_details_edit
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            with(toolbar) {
                title.setText(R.string.editing)
                optionsButton.visibility = View.INVISIBLE
                backButton.setOnClickListener {
                    requireActivity().onBackPressed()
                }
            }
            saveButton.setOnClickListener {
//                findNavController().navigate()
            }
        }
    }
}
