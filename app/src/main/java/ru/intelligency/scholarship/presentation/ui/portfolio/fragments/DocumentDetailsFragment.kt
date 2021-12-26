package ru.intelligency.scholarship.presentation.ui.portfolio.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentDocumentDetailsBinding
import ru.intelligency.scholarship.domain.portfolio.PortfolioInteractor
import ru.intelligency.scholarship.domain.portfolio.model.Document
import ru.intelligency.scholarship.presentation.App
import ru.intelligency.scholarship.presentation.base.BaseFragment
import ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels.DocumentDetailsViewModel
import ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels.DocumentDetailsViewModelFactory
import ru.intelligency.scholarship.presentation.utils.Status
import javax.inject.Inject

class DocumentDetailsFragment : BaseFragment<FragmentDocumentDetailsBinding>() {

    private val args: DocumentDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var interactor: PortfolioInteractor
    private val viewModel: DocumentDetailsViewModel by viewModels {
        DocumentDetailsViewModelFactory(interactor, args.documentId)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_document_details
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding.toolbar) {
            backButton.setOnClickListener {
                requireActivity().onBackPressed()
            }
            optionsButton.setOnClickListener {
                showMenu(it, R.menu.document_details_popup_menu)
            }
        }

        collectDocument()
    }

    private fun showMenu(view: View, menuRes: Int) {
        val popup = PopupMenu(requireContext(), view)
        popup.menuInflater.inflate(menuRes, popup.menu)
        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.delete -> {
                    // TODO: delete from data source and get back to PortFolioFragment
                    Toast.makeText(requireContext(), "Удаление", Toast.LENGTH_SHORT).show()
                }
                R.id.edit -> {
                    findNavController().navigate(
                        DocumentDetailsFragmentDirections
                            .actionDocumentDetailsFragmentToDocumentDetailsEditFragment(args.documentId)
                    )
                }
            }
            true
        }
        popup.show()
    }

    private fun collectDocument() {
        lifecycleScope.launch {
            viewModel.document.collect { document ->
                with(binding) {
                    eventType.text = document.eventType
                    eventStatus.text = document.eventStatus
                    documentDateReceipt.text = viewModel.getModifiedReceiptDateString(document)
                    eventLocation.text = document.eventLocation
                    toolbar.title.text = document.title
                }
                setStatusMessage(document)
            }
        }
    }

    private fun setStatusMessage(document: Document) {
        when (document.documentStatus) {
            Status.IN_WAITING -> {
                setStatusMessageAwaiting()
            }
            Status.ACCEPTED -> {
                setStatusMessageAccepted()
            }
            Status.REJECTED -> {
                setStatusMessageRejected()
            }
        }
    }

    private fun setStatusMessageAwaiting() {
        with(binding.statusMessage) {
            message.text = getString(R.string.document_awaiting)
            messageIcon.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_waiting)
            )
            rootLayout.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_awaiting)
        }
    }

    private fun setStatusMessageAccepted() {
        with(binding.statusMessage) {
            message.text = getString(R.string.document_accepted)
            messageIcon.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_accepted)
            )
            rootLayout.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_accepted)
        }
    }

    private fun setStatusMessageRejected() {
        with(binding.statusMessage) {
            message.text = getString(R.string.document_rejected)
            messageIcon.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_rejected)
            )
            rootLayout.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_rejected)
        }
    }
}
