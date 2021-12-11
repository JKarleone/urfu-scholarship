package ru.intelligency.scholarship.presentation.entrance

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentSignUpPart2Binding
import ru.intelligency.scholarship.presentation.base.BaseFragment
import ru.intelligency.scholarship.presentation.utils.ClickableSpanStringHelper

class SignUpPart2Fragment : BaseFragment<FragmentSignUpPart2Binding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_sign_up_part2
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setClickableSpanString()
        binding.registrationButton.setOnClickListener(::onRegistrationButtonClick)
    }

    private fun setClickableSpanString() {
        ClickableSpanStringHelper.makeTextClickable(
            textView = binding.confirmationTextView,
            isUnderlineText = true,
            startIndex = 13,
            endIndex = binding.confirmationTextView.text.length,
        ) {
            Toast.makeText(requireContext(), R.string.feature_will_be_soon, Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun onRegistrationButtonClick(view: View) {
        Toast.makeText(requireContext(), R.string.successful_registration, Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_registrationPart2Fragment_to_signInFragment)
    }
}
