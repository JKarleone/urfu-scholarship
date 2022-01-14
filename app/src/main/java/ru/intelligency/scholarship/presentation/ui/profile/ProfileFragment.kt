package ru.intelligency.scholarship.presentation.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentProfileBinding
import ru.intelligency.scholarship.domain.profile.models.Profile
import ru.intelligency.scholarship.presentation.App
import ru.intelligency.scholarship.presentation.base.BaseFragment
import ru.intelligency.scholarship.presentation.entrance.EntranceActivity
import javax.inject.Inject

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    @Inject
    lateinit var viewModelFactory: ProfileViewModelFactory
    private val viewModel: ProfileViewModel by viewModels { viewModelFactory }

    override fun getLayoutId(): Int {
        return R.layout.fragment_profile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeProfile()
        binding.editButton.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_profileEditFragment)
        }
        binding.logoutButton.setOnClickListener {
            val intent = Intent(requireContext(), EntranceActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
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
            fullName.text = profile.fullName
            academicGroupNumber.text = profile.academicGroupNumber
            email.text = profile.email
            phoneNumber.text = profile.phoneNumber
        }
    }
}
