package ru.intelligency.scholarship.presentation.ui.portfolio.fragments

import android.os.Bundle
import android.view.View
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
        lifecycleScope.launch {
            viewModel.document.collect { document ->
                with(binding) {
                    documentNameInputLayout.editText?.setText(document.title)
                    eventTypeInputLayout.editText?.setText(document.eventType)
                    eventStatusInputLayout.editText?.setText(document.eventStatus)
                    val (day, month, year) = document.dateOfReceipt
                    documentDateInputLayout.editText?.setText(
                        getString(R.string.default_date, day, month, year)
                    )
                    eventPlaceInputLayout.editText?.setText(document.eventLocation)
                }
            }
        }
    }
}
