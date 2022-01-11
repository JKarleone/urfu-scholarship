package ru.intelligency.scholarship.presentation.ui.portfolio.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentScanDocumentInfoBinding
import ru.intelligency.scholarship.domain.portfolio.model.Document
import ru.intelligency.scholarship.presentation.App
import ru.intelligency.scholarship.presentation.base.BaseFragment
import ru.intelligency.scholarship.presentation.extensions.toDate
import javax.inject.Inject

class ScanDocumentInfoFragment : BaseFragment<FragmentScanDocumentInfoBinding>() {

    @Inject
    lateinit var viewModelFactory: NewDocumentViewModelFactory
    private val viewModel: NewDocumentViewModel by viewModels(
        ownerProducer = { requireActivity() },
        factoryProducer = { viewModelFactory }
    )
    private val eventTypesItems by lazy { viewModel.getDefaultEventTypes() }
    private val eventStatusesItems by lazy { viewModel.getDefaultEventStatuses() }

    override fun getLayoutId(): Int {
        return R.layout.fragment_scan_document_info
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
            saveButton.setOnClickListener {
                checkInfoAndNavigate()
            }
        }
        setupAdaptersForAutoCompleteViews()
    }

    private fun setupAdaptersForAutoCompleteViews() {
        with(binding.eventTypeInputLayoutExposed.editText as AutoCompleteTextView) {
            val typesAdapter =
                ArrayAdapter(
                    requireContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    eventTypesItems
                )
            setAdapter(typesAdapter)
            setOnItemClickListener { _, _, position, _ ->
                binding.eventTypeInputLayout.visibility =
                    if (position == eventTypesItems.size - 1) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }
        }
        with(binding.eventStatusInputLayoutExposed.editText as AutoCompleteTextView) {
            val statusAdapter =
                ArrayAdapter(
                    requireContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    eventStatusesItems
                )
            setAdapter(statusAdapter)
            setOnItemClickListener { _, _, position, _ ->
                binding.eventStatusInputLayout.visibility =
                    if (position == eventStatusesItems.size - 1) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }
        }
    }

    private fun checkInfoAndNavigate() {
        with(binding) {
            val eventTypeExposed = binding.eventTypeInputLayoutExposed.editText?.text.toString()
            val eventTypeInput = binding.eventTypeInputLayout.editText?.text.toString()
            val eventType =
                if (eventTypeExposed == eventTypesItems.last()) eventTypeInput else eventTypeExposed
            val eventStatusExposed = binding.eventStatusInputLayoutExposed.editText?.text.toString()
            val eventStatusInput = binding.eventStatusInputLayout.editText?.text.toString()
            val eventStatus =
                if (eventStatusExposed == eventStatusesItems.last()) eventStatusInput else eventStatusExposed
            val dateOfReceipt = documentDateInputLayout.editText?.text.toString().toDate()
            val newDoc = Document(
                title = documentNameInputLayout.editText?.text.toString(),
                eventType = eventType,
                eventStatus = eventStatus,
                dateOfReceipt = dateOfReceipt,
                eventLocation = binding.eventPlaceInputLayout.editText?.text.toString()
            )
            lifecycleScope.launch {
                viewModel.createDocument(newDoc)
                findNavController().navigate(R.id.action_scanDocumentInfoFragment_to_navigation_portfolio)
            }
        }
    }
}
