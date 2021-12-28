package ru.intelligency.scholarship.presentation.ui.myapplications.fragments

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.fragment.app.viewModels
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentCreateNewApplicationBinding
import ru.intelligency.scholarship.domain.myapplications.models.Application
import ru.intelligency.scholarship.domain.portfolio.model.SimpleDate
import ru.intelligency.scholarship.presentation.App
import ru.intelligency.scholarship.presentation.base.BaseFragment
import ru.intelligency.scholarship.presentation.ui.myapplications.viewmodels.ApplicationsViewModel
import ru.intelligency.scholarship.presentation.ui.myapplications.viewmodels.ApplicationsViewModelFactory
import ru.intelligency.scholarship.presentation.utils.Status
import java.time.LocalDate
import javax.inject.Inject

class CreateNewApplicationFragment : BaseFragment<FragmentCreateNewApplicationBinding>() {

    @Inject
    lateinit var viewModelFactory: ApplicationsViewModelFactory
    private val viewModel: ApplicationsViewModel by viewModels { viewModelFactory }

    override fun getLayoutId(): Int {
        return R.layout.fragment_create_new_application
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val arr = arrayOf(
            "Именная стипендия",
            "Повышенная стипендия за достижения в учебной деятельности",
            "Повышенная стипендия за достижения в научно-исследовательской деятельности"
        )
        with(binding.scholarshipType.editText as AutoCompleteTextView) {
            val typesAdapter =
                ArrayAdapter(
                    requireContext(),
                    R.layout.support_simple_spinner_dropdown_item,
                    arr
                )
            setAdapter(typesAdapter)
        }
        bindSaveButtonClick()
    }

    private fun bindSaveButtonClick() {
        binding.saveButton.setOnClickListener {
            val newApplication = Application(
                scholarshipType = binding.scholarshipType.editText?.text.toString(),
                fullName = binding.fullName.editText?.text.toString(),
                academicGroupNumber = binding.academicGroupNumber.editText?.text.toString(),
                specialityCode = binding.specialityCode.editText?.text.toString(),
                specialityName = binding.specialityName.editText?.text.toString(),
                totalMarksCount = binding.totalMarksCount.editText?.text.toString().toInt(),
                excellentMarksCount = binding.excellentMarksCount.editText?.text.toString().toInt(),
                applicationStatus = Status.IN_WAITING,
                sendingDate = with(LocalDate.now()) { SimpleDate(dayOfMonth, monthValue, year) }
            )
            viewModel.saveApplication(newApplication)
            requireActivity().onBackPressed()
        }
    }
}
