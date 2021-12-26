package ru.intelligency.scholarship.presentation.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentProfileEditBinding
import ru.intelligency.scholarship.domain.profile.models.Profile
import ru.intelligency.scholarship.presentation.App
import ru.intelligency.scholarship.presentation.base.BaseFragment
import javax.inject.Inject

class ProfileEditFragment : BaseFragment<FragmentProfileEditBinding>() {

    @Inject
    lateinit var viewModelFactory: ProfileViewModelFactory
    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }

    override fun getLayoutId(): Int {
        return R.layout.fragment_profile_edit
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupToolbar()
        observeProfile()
        binding.saveButton.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun setupToolbar() {
        with(binding.toolbar) {
            backButton.setOnClickListener { requireActivity().onBackPressed() }
            title.setText(R.string.editing)
            optionsButton.visibility = View.INVISIBLE
        }
    }

    private fun observeProfile() {
        lifecycleScope.launch {
            viewModel.getProfile().collect { profile ->
                fillFieldsWithProfile(profile)
            }
        }
    }

    private fun fillFieldsWithProfile(profile: Profile) {
        with(binding) {
            fullName.editText?.setText(profile.fullName)
            academicGroupNumber.editText?.setText(profile.academicGroupNumber)
            email.editText?.setText(profile.email)
            phoneNumber.editText?.setText(profile.phoneNumber)
        }
    }
}
