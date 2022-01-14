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
import ru.intelligency.scholarship.presentation.extensions.checkField
import ru.intelligency.scholarship.presentation.extensions.hideKeyboard
import ru.intelligency.scholarship.presentation.extensions.matchEmail
import javax.inject.Inject

class ProfileEditFragment : BaseFragment<FragmentProfileEditBinding>() {

    @Inject
    lateinit var viewModelFactory: ProfileViewModelFactory
    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }
    private var profileId: Int? = null

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
            tryToGetProfile()?.let { profile ->
                viewModel.updateProfile(profile)
                requireActivity().onBackPressed()
            }
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
                profileId = profile.id
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

    private fun tryToGetProfile(): Profile? {
        val fullName = binding.fullName.checkField()
        val academicGroupNumber = binding.academicGroupNumber.checkField()
        val email = binding.email.checkField(matches = String::matchEmail)
        val phoneNumber = binding.phoneNumber.checkField()

        hideKeyboard()

        return if (fullName.isNotEmpty() &&
            academicGroupNumber.isNotEmpty() &&
            email.isNotEmpty() &&
            phoneNumber.isNotEmpty()
        ) {
            profileId?.let { id ->
                Profile(id, fullName, academicGroupNumber, email, phoneNumber)
            }
        } else {
            null
        }
    }
}
