package ru.intelligency.scholarship.presentation.entrance

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentSignInBinding
import ru.intelligency.scholarship.presentation.MainActivity
import ru.intelligency.scholarship.presentation.base.BaseFragment
import ru.intelligency.scholarship.presentation.utils.ClickableSpanStringHelper

class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_sign_in
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setClickableSpanString()
        binding.forgetPasswordTextView.setOnClickListener(::onForgetPasswordClick)
        binding.signInButton.setOnClickListener(this::onSignUpButtonClick)
    }

    private fun setClickableSpanString() {
        ClickableSpanStringHelper.makeTextClickable(
            textView = binding.registerTextView,
            isUnderlineText = false,
            startIndex = 18,
            endIndex = binding.registerTextView.text.length,
        ) {
            findNavController().navigate(R.id.action_signInFragment_to_registrationPart1Fragment)
        }
    }

    private fun onForgetPasswordClick(view: View) {
        Toast.makeText(requireContext(), R.string.forget_password_congrats, Toast.LENGTH_LONG)
            .show()
    }

    private fun onSignUpButtonClick(view: View) {
        val intent = Intent(requireContext(), MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
