package ru.intelligency.scholarship.presentation.ui.portfolio.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentDocumentDetailsEditBinding
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor
import ru.intelligency.scholarship.presentation.App
import ru.intelligency.scholarship.presentation.base.BaseFragment
import ru.intelligency.scholarship.presentation.extensions.getStringDate
import ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels.DocumentDetailsEditViewModel
import ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels.DocumentDetailsEditViewModelFactory
import javax.inject.Inject

class DocumentDetailsEditFragment : BaseFragment<FragmentDocumentDetailsEditBinding>() {

    private val args: DocumentDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var interactor: PortfolioInteractor
    private val viewModel: DocumentDetailsEditViewModel by viewModels {
        DocumentDetailsEditViewModelFactory(interactor, args.documentId)
    }
    private val defaultEventTypes by lazy { viewModel.getDefaultEventTypes() }
    private val defaultEventStatuses by lazy { viewModel.getDefaultEventStatuses() }

    override fun getLayoutId(): Int {
        return R.layout.fragment_document_details_edit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).appComponent.inject(this)
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
                // TODO: validate fields
                // TODO: navigate if fields are ok
            }
            deleteButton.setOnClickListener {
                // TODO: delete from data source and get back to PortFolioFragment
            }

        }
        initAdapters()
        fillFields()
    }

    private fun initAdapters() {
        with(binding.eventTypeInputLayoutExposed.editText as AutoCompleteTextView) {
            val typesAdapter =
                ArrayAdapter(
                    requireContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    defaultEventTypes
                )
            setAdapter(typesAdapter)
            setOnItemClickListener { _, _, position, _ ->
                binding.eventTypeInputLayout.visibility =
                    if (position == defaultEventTypes.size - 1) {
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
                    defaultEventStatuses
                )
            setAdapter(statusAdapter)
            setOnItemClickListener { _, _, position, _ ->
                binding.eventStatusInputLayout.visibility =
                    if (position == defaultEventStatuses.size - 1) {
                        View.VISIBLE
                    } else {
                        View.GONE
                    }
            }
        }
    }

    private fun fillFields() {
        lifecycleScope.launch {
            viewModel.document.collect { document ->
                with(binding) {
                    documentNameInputLayout.editText?.setText(document.title)
                    documentDateInputLayout.editText?.setText(document.dateOfReceipt.getStringDate())
                    eventPlaceInputLayout.editText?.setText(document.eventLocation)
                    setEventType(document.eventType)
                    setEventStatus(document.eventStatus)
                }
            }
        }
    }

    private fun setEventType(eventType: String) {
        with(binding) {
            if (eventType in defaultEventTypes) {
                eventTypeExposed.setText(eventType, false)
                eventTypeInputLayout.editText?.setText("")
                eventTypeInputLayout.visibility = View.GONE
            } else {
                eventTypeExposed.setText(defaultEventTypes.last(), false)
                eventTypeInputLayout.visibility = View.VISIBLE
                eventTypeInputLayout.editText?.setText(eventType)
            }
        }
    }

    private fun setEventStatus(eventStatus: String) {
        with(binding) {
            if (eventStatus in defaultEventStatuses) {
                eventStatusExposed.setText(eventStatus, false)
                eventStatusInputLayout.editText?.setText("")
                eventStatusInputLayout.visibility = View.GONE
            } else {
                eventStatusExposed.setText(defaultEventStatuses.last(), false)
                eventStatusInputLayout.visibility = View.VISIBLE
                eventStatusInputLayout.editText?.setText(eventStatus)
            }
        }
    }
}
