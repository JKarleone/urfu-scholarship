package ru.intelligency.scholarship.presentation.entrance

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentSignUpPart1Binding
import ru.intelligency.scholarship.presentation.base.BaseFragment
import ru.intelligency.scholarship.presentation.utils.ClickableSpanStringHelper

class SignUpPart1Fragment : BaseFragment<FragmentSignUpPart1Binding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_sign_up_part1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setClickableSpanString()
        binding.nextStepButton.setOnClickListener(::onNextStepButtonClick)
    }

    private fun setClickableSpanString() {
        ClickableSpanStringHelper.makeTextClickable(
            textView = binding.alreadyRegisterTextView,
            isUnderlineText = false,
            startIndex = 22,
            endIndex = binding.alreadyRegisterTextView.text.length,
        ) {
            findNavController().navigate(R.id.action_registrationPart1Fragment_to_signInFragment)
        }
    }

    private fun onNextStepButtonClick(view: View) {
        findNavController().navigate(
            R.id.action_registrationPart1Fragment_to_registrationPart2Fragment
        )
    }
}
