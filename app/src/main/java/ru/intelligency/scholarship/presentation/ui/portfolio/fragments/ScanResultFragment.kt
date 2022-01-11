package ru.intelligency.scholarship.presentation.ui.portfolio.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentScanResultBinding
import ru.intelligency.scholarship.presentation.App
import ru.intelligency.scholarship.presentation.base.BaseFragment
import javax.inject.Inject

class ScanResultFragment : BaseFragment<FragmentScanResultBinding>() {

    @Inject
    lateinit var viewModelFactory: NewDocumentViewModelFactory
    private val viewModel: NewDocumentViewModel by viewModels(
        ownerProducer = { requireActivity() },
        factoryProducer = { viewModelFactory }
    )

    override fun getLayoutId(): Int {
        return R.layout.fragment_scan_result
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).appComponent.inject(this)
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
            viewModel.image?.let { img ->
                documentImage.setImageBitmap(img)
            }
            nextButton.setOnClickListener {
                findNavController().navigate(R.id.action_scanResultFragment_to_scanDocumentInfoFragment)
            }
        }
    }
}
