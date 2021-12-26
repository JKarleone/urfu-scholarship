package ru.intelligency.scholarship.presentation.ui.myapplications.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentMyApplicationsBinding
import ru.intelligency.scholarship.domain.myapplications.models.Application
import ru.intelligency.scholarship.presentation.App
import ru.intelligency.scholarship.presentation.base.BaseFragment
import ru.intelligency.scholarship.presentation.ui.myapplications.adapter.ApplicationsAdapter
import ru.intelligency.scholarship.presentation.ui.myapplications.viewmodels.ApplicationsViewModel
import ru.intelligency.scholarship.presentation.ui.myapplications.viewmodels.ApplicationsViewModelFactory
import javax.inject.Inject

class MyApplicationsFragment : BaseFragment<FragmentMyApplicationsBinding>(),
    ApplicationsAdapter.OnApplicationItemClickListener {

    @Inject
    lateinit var viewModelFactory: ApplicationsViewModelFactory
    private val viewModel: ApplicationsViewModel by viewModels { viewModelFactory }
    private val applicationsAdapter = ApplicationsAdapter(listOf(), this)

    override fun getLayoutId(): Int {
        return R.layout.fragment_my_applications
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        observeApplications()
    }

    private fun observeApplications() {
        lifecycleScope.launch {
            viewModel.applications.collect { applications ->
                applicationsAdapter.submitData(applications)
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.applicationsRecyclerView) {
            adapter = applicationsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onApplicationItemClick(application: Application) {
        findNavController().navigate(
            MyApplicationsFragmentDirections.actionNavigationMyApplicationsToApplicationDetailsFragment(
                application.id
            )
        )
    }
}
