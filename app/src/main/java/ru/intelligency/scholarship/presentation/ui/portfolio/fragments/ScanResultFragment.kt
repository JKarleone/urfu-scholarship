package ru.intelligency.scholarship.presentation.ui.portfolio.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentScanResultBinding
import ru.intelligency.scholarship.presentation.base.BaseFragment

class ScanResultFragment : BaseFragment<FragmentScanResultBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_scan_result
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
            nextButton.setOnClickListener {
                findNavController().navigate(R.id.action_scanResultFragment_to_scanDocumentInfoFragment)
            }
        }
    }
}
