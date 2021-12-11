package ru.intelligency.scholarship.presentation.ui.portfolio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.ItemPortfolioDocumentBinding
import ru.intelligency.scholarship.presentation.ui.portfolio.model.PortfolioDocument
import ru.intelligency.scholarship.presentation.utils.DocumentStatus
import java.text.SimpleDateFormat
import java.util.*

class DocumentsAdapter(
    private var documents: List<PortfolioDocument>
) : RecyclerView.Adapter<DocumentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPortfolioDocumentBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(documents[position])
    }

    override fun getItemCount(): Int {
        return documents.size
    }

    class ViewHolder(
        private val binding: ItemPortfolioDocumentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PortfolioDocument) {
            val context = binding.root.context
            val statusImage = when (item.status) {
                DocumentStatus.ACCEPTED -> R.drawable.ic_accepted
                DocumentStatus.IN_WAITING -> R.drawable.ic_waiting
                DocumentStatus.REJECTED -> R.drawable.ic_rejected
            }
            val statusText = when (item.status) {
                DocumentStatus.ACCEPTED -> {
                    val simpleFormatter = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                    val date = simpleFormatter.format(item.expirationDate)
                    context.getString(R.string.portfolio_profile_expiration_date, date)
                }
                DocumentStatus.IN_WAITING -> {
                    "Ожидает верификации"
                }
                DocumentStatus.REJECTED -> {
                    "Срок действия истек"
                }
            }

            binding.statusImageView.setImageResource(statusImage)
            binding.nameTextView.text = item.name
            binding.descriptionTextView.text = item.description
            binding.expirationDateTextView.text = statusText
        }
    }
}
