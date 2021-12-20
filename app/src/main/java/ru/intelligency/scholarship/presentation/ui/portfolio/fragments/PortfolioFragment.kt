package ru.intelligency.scholarship.presentation.ui.portfolio.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentPortfolioBinding
import ru.intelligency.scholarship.presentation.App
import ru.intelligency.scholarship.presentation.base.BaseFragment
import ru.intelligency.scholarship.presentation.ui.portfolio.adapter.DocumentsAdapter
import ru.intelligency.scholarship.presentation.ui.portfolio.model.PortfolioDocument
import ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels.PortfolioViewModel
import ru.intelligency.scholarship.presentation.ui.portfolio.viewmodels.PortfolioViewModelFactory
import javax.inject.Inject

class PortfolioFragment : BaseFragment<FragmentPortfolioBinding>(),
    DocumentsAdapter.OnDocumentItemClickListener {

    @Inject
    lateinit var viewModelFactory: PortfolioViewModelFactory
    private val viewModel: PortfolioViewModel by viewModels {
        viewModelFactory
    }
    private val adapter = DocumentsAdapter(listOf(), this)

    override fun getLayoutId(): Int {
        return R.layout.fragment_portfolio
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (requireActivity().application as App).appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        binding.addButton.setOnClickListener {
            addButtonClicked()
        }
        lifecycleScope.launch {
            viewModel.documents.collect { list ->
                adapter.submitData(list)
            }
            viewModel.documents
        }
    }

    private fun setupRecyclerView() {
        binding.documentsRecyclerView.adapter = adapter
        binding.documentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun addButtonClicked() {
        findNavController().navigate(R.id.action_navigation_portfolio_to_scanDocumentFragment)
    }

    override fun onDocumentItemClick(document: PortfolioDocument) {
        Log.d(TAG, "Document clicked!\n$document")
        findNavController().navigate(
            PortfolioFragmentDirections.actionNavigationPortfolioToDocumentDetailsFragment(document.id)
        )
    }

    companion object {

        private const val TAG = "PortfolioFragment"
    }
}
