package ru.intelligency.scholarship.presentation.ui.portfolio

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.FragmentPortfolioBinding
import ru.intelligency.scholarship.presentation.base.BaseFragment
import ru.intelligency.scholarship.presentation.ui.portfolio.adapter.DocumentsAdapter
import ru.intelligency.scholarship.presentation.ui.portfolio.model.PortfolioDocument
import ru.intelligency.scholarship.presentation.utils.DocumentStatus
import java.util.*

class PortfolioFragment : BaseFragment<FragmentPortfolioBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_portfolio
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        binding.addButton.setOnClickListener {
            addButtonClicked()
        }
    }

    private fun setupRecyclerView() {
        val documents = listOf(
            PortfolioDocument(
                0,
                "Сертификат",
                "Хакатон, Международное",
                DocumentStatus.ACCEPTED,
                Date()
            ),
            PortfolioDocument(
                0,
                "Диплом",
                "Хакатон, Международное",
                DocumentStatus.IN_WAITING,
                Date()
            ),
        )
        binding.documentsRecyclerView.adapter = DocumentsAdapter(documents)
        binding.documentsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun addButtonClicked() {
        Log.d("PortfolioFragment", "Button Clicked!")
    }
}
