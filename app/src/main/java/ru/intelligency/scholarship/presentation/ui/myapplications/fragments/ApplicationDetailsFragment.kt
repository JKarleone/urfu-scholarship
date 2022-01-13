package ru.intelligency.scholarship.presentation.ui.myapplications.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.data.portfolio.ImageProvider
import ru.intelligency.scholarship.databinding.FragmentApplicationDetailsBinding
import ru.intelligency.scholarship.domain.myapplications.models.Application
import ru.intelligency.scholarship.domain.portfolio.model.Document
import ru.intelligency.scholarship.presentation.App
import ru.intelligency.scholarship.presentation.base.BaseFragment
import ru.intelligency.scholarship.presentation.ui.myapplications.viewmodels.ApplicationsViewModel
import ru.intelligency.scholarship.presentation.ui.myapplications.viewmodels.ApplicationsViewModelFactory
import ru.intelligency.scholarship.presentation.ui.portfolio.adapter.DocumentsAdapter
import ru.intelligency.scholarship.presentation.ui.portfolio.extensions.toPortfolioDocument
import ru.intelligency.scholarship.presentation.utils.Status
import javax.inject.Inject

class ApplicationDetailsFragment : BaseFragment<FragmentApplicationDetailsBinding>() {

    private val args: ApplicationDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var viewModelFactory: ApplicationsViewModelFactory
    private val viewModel: ApplicationsViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var imageProvider: ImageProvider
    private val adapter by lazy { DocumentsAdapter(listOf(), null, imageProvider) }

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_application_details
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()
        setupRecyclerView()
        getApplicationAndFillFields()
    }

    private fun setupToolbar() {
        with(binding.toolbar) {
            backButton.setOnClickListener { requireActivity().onBackPressed() }
            optionsButton.visibility = View.INVISIBLE
        }
    }

    private fun getApplicationAndFillFields() {
        lifecycleScope.launch {
            viewModel.getApplication(args.applicationId).collect { applicationWithDocs ->
                applicationWithDocs?.let { appWithDocs ->
                    adapter.submitData(appWithDocs.documents.map(Document::toPortfolioDocument))
                    fillFieldsWithApplication(appWithDocs.application)
                    binding.cancelButton.setOnClickListener {
                        lifecycleScope.launch {
                            viewModel.deleteApplication(applicationWithDocs.application.id)
                            requireActivity().onBackPressed()
                        }
                    }
                }
            }
        }
    }

    private fun fillFieldsWithApplication(application: Application) {
        with(binding) {
            toolbar.title.text = application.scholarshipType
            scholarshipType.text = application.scholarshipType
            fullName.text = application.fullName
            academicGroupNumber.text = application.academicGroupNumber
            specialityCode.text = application.specialityCode
            specialityName.text = application.specialityName
            totalMarksCount.text = application.totalMarksCount.toString()
            excellentMarksCount.text = application.excellentMarksCount.toString()
        }
        when (application.applicationStatus) {
            Status.ACCEPTED -> {
                setStatusMessage(
                    R.string.application_accepted,
                    R.drawable.ic_accepted,
                    R.drawable.bg_accepted
                )
                binding.cancelButton.visibility = View.GONE
            }
            Status.REJECTED -> {
                setStatusMessage(
                    R.string.application_rejected,
                    R.drawable.ic_rejected,
                    R.drawable.bg_rejected
                )
                binding.cancelButton.visibility = View.GONE
            }
            Status.IN_WAITING -> {
                setStatusMessage(
                    R.string.application_awaiting,
                    R.drawable.ic_waiting,
                    R.drawable.bg_awaiting
                )
            }
        }
    }

    private fun setStatusMessage(
        @StringRes messageRes: Int,
        @DrawableRes iconRes: Int,
        @DrawableRes bgRes: Int
    ) {
        with(binding.statusMessage) {
            message.text = getString(messageRes)
            messageIcon.setImageDrawable(
                ContextCompat.getDrawable(requireContext(), iconRes)
            )
            rootLayout.background =
                ContextCompat.getDrawable(requireContext(), bgRes)
        }
    }

    private fun setupRecyclerView() {
        binding.documentsRecyclerView.adapter = adapter
        binding.documentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}
