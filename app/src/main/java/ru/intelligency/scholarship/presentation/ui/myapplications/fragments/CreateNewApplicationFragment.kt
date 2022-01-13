package ru.intelligency.scholarship.presentation.ui.myapplications.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.data.portfolio.ImageProvider
import ru.intelligency.scholarship.databinding.FragmentCreateNewApplicationBinding
import ru.intelligency.scholarship.domain.myapplications.models.Application
import ru.intelligency.scholarship.presentation.App
import ru.intelligency.scholarship.presentation.base.BaseFragment
import ru.intelligency.scholarship.presentation.ui.myapplications.adapter.DocumentSelectionAdapter
import ru.intelligency.scholarship.presentation.ui.myapplications.viewmodels.ApplicationsViewModel
import ru.intelligency.scholarship.presentation.ui.myapplications.viewmodels.ApplicationsViewModelFactory
import ru.intelligency.scholarship.presentation.utils.Status
import java.util.Calendar
import javax.inject.Inject

class CreateNewApplicationFragment : BaseFragment<FragmentCreateNewApplicationBinding>() {

    @Inject
    lateinit var viewModelFactory: ApplicationsViewModelFactory
    private val viewModel: ApplicationsViewModel by viewModels { viewModelFactory }

    @Inject
    lateinit var imageProvider: ImageProvider
    private val adapter by lazy { DocumentSelectionAdapter(listOf(), imageProvider) }

    override fun getLayoutId(): Int {
        return R.layout.fragment_create_new_application
    }

    override fun onAttach(context: Context) {
        (requireActivity().application as App).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding.scholarshipType.editText as AutoCompleteTextView) {
            val typesAdapter = ArrayAdapter(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item,
                applicationTypes
            )
            setAdapter(typesAdapter)
        }
        bindSaveButtonClick()
        setupToolbar()
        setupRecyclerView()
    }

    private fun bindSaveButtonClick() {
        binding.saveButton.setOnClickListener {
            lifecycleScope.launch {
                val newApplication = Application(
                    scholarshipType = binding.scholarshipType.editText?.text.toString(),
                    fullName = binding.fullName.editText?.text.toString(),
                    academicGroupNumber = binding.academicGroupNumber.editText?.text.toString(),
                    specialityCode = binding.specialityCode.editText?.text.toString(),
                    specialityName = binding.specialityName.editText?.text.toString(),
                    totalMarksCount = binding.totalMarksCount.editText?.text.toString().toInt(),
                    excellentMarksCount = binding.excellentMarksCount.editText?.text.toString()
                        .toInt(),
                    applicationStatus = Status.IN_WAITING,
                    sendingDate = Calendar.getInstance().timeInMillis
                )
                viewModel.saveApplication(newApplication, adapter.selectedDocsIds)
                requireActivity().onBackPressed()
            }
        }
    }

    private fun setupToolbar() {
        with(binding.toolbar) {
            optionsButton.visibility = View.INVISIBLE
            backButton.setOnClickListener { requireActivity().onBackPressed() }
        }
    }

    private fun setupRecyclerView() {
        binding.documentsRecyclerView.adapter = adapter
        binding.documentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            viewModel.documents.collect { documents ->
                adapter.submitData(documents)
            }
        }
    }

    companion object {
        private val applicationTypes = arrayOf(
            "Именная стипендия",
            "Повышенная стипендия за достижения в учебной деятельности",
            "Повышенная стипендия за достижения в научно-исследовательской деятельности"
        )
    }
}
