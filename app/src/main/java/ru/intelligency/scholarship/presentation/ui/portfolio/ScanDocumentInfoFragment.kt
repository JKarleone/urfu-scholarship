package ru.intelligency.scholarship.presentation.ui.portfolio

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentScanDocumentInfoBinding
import ru.intelligency.scholarship.presentation.base.BaseFragment

class ScanDocumentInfoFragment : BaseFragment<FragmentScanDocumentInfoBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_scan_document_info
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            with(toolbar) {
                title.setText(R.string.document_add_doc)
                optionsButton.visibility = View.INVISIBLE
                backButton.setOnClickListener {
                    requireActivity().onBackPressed()
                }
            }
            saveButton.setOnClickListener {
                findNavController().navigate(R.id.action_scanDocumentInfoFragment_to_navigation_portfolio)
            }
        }
    }
}
