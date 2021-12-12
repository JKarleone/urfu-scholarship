package ru.intelligency.scholarship.presentation.ui.portfolio

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.data.portfolio.DocumentsRepositoryImpl
import ru.intelligency.scholarship.databinding.FragmentPortfolioBinding
import ru.intelligency.scholarship.domain.portfolio.DocumentsInteractor
import ru.intelligency.scholarship.presentation.base.BaseFragment
import ru.intelligency.scholarship.presentation.ui.portfolio.adapter.DocumentsAdapter
import ru.intelligency.scholarship.presentation.ui.portfolio.model.PortfolioDocument

class PortfolioFragment : BaseFragment<FragmentPortfolioBinding>(),
    DocumentsAdapter.OnDocumentItemClickListener {

    private val repository = DocumentsRepositoryImpl()
    private val interactor = DocumentsInteractor(repository)
    private val viewModel: PortfolioViewModel by viewModels {
        PortfolioViewModelFactory(interactor)
    }
    private val adapter = DocumentsAdapter(listOf(), this)

    override fun getLayoutId(): Int {
        return R.layout.fragment_portfolio
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
        }
    }

    private fun setupRecyclerView() {
        binding.documentsRecyclerView.adapter = adapter
        binding.documentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun addButtonClicked() {
        Log.d(TAG, "Button Clicked!")
    }

    override fun onDocumentItemClick(document: PortfolioDocument) {
        Log.d(TAG, "Document clicked!\n$document")
    }

    companion object {

        private const val TAG = "PortfolioFragment"
    }
}
