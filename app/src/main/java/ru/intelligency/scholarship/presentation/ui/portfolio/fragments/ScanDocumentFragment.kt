package ru.intelligency.scholarship.presentation.ui.portfolio.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentScanDocumentBinding
import ru.intelligency.scholarship.presentation.App
import ru.intelligency.scholarship.presentation.base.BaseFragment
import javax.inject.Inject

class ScanDocumentFragment : BaseFragment<FragmentScanDocumentBinding>() {

    @Inject
    lateinit var viewModelFactory: NewDocumentViewModelFactory
    private val viewModel: NewDocumentViewModel by viewModels(
        ownerProducer = { requireActivity() },
        factoryProducer = { viewModelFactory }
    )

    private val cameraResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val image = result.data?.extras?.get("data") as? Bitmap
                image?.let { img ->
                    viewModel.image = img
                    binding.documentScanner.setImage(img)
                } ?: run {
                    requireActivity().onBackPressed()
                }
            } else {
                requireActivity().onBackPressed()
            }
        }
    private val permissionResult = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            startCamera()
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_scan_document
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            with(toolbar) {
                title.setText(R.string.document_add_doc)
                optionsButton.visibility = View.INVISIBLE
                backButton.setOnClickListener {
                    requireActivity().onBackPressed()
                }
            }
            nextButton.setOnClickListener {
                lifecycleScope.launch {
                    with(binding) {
                        viewModel.image = documentScanner.getCroppedImage()
                        findNavController().navigate(R.id.action_scanDocumentFragment_to_scanResultFragment)
                    }
                }
            }
            if (viewModel.image == null) {
                tryToStartCamera()
            }
        }
    }

    private fun tryToStartCamera() {
        if (ContextCompat.checkSelfPermission(
                requireContext(), CAMERA_PERMISSION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            startCamera()
        } else {
            permissionResult.launch(CAMERA_PERMISSION)
        }
    }

    private fun startCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraResult.launch(intent)
    }

    companion object {

        private const val CAMERA_PERMISSION = Manifest.permission.CAMERA
    }
}
