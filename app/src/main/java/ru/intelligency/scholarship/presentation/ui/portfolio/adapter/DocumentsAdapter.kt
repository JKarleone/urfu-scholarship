package ru.intelligency.scholarship.presentation.ui.portfolio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.ItemPortfolioDocumentBinding
import ru.intelligency.scholarship.presentation.ui.portfolio.model.PortfolioDocument
import ru.intelligency.scholarship.presentation.utils.DocumentStatus

class DocumentsAdapter(
    private var documents: List<PortfolioDocument>,
    private val clickListener: OnDocumentItemClickListener
) : RecyclerView.Adapter<DocumentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPortfolioDocumentBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDocument = documents[position]
        holder.bind(currentDocument)
        holder.itemView.setOnClickListener {
            clickListener.onDocumentItemClick(currentDocument)
        }
    }

    override fun getItemCount(): Int {
        return documents.size
    }

    fun submitData(list: List<PortfolioDocument>) {
        this.documents = list
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemPortfolioDocumentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PortfolioDocument) {
            val context = binding.root.context
            val statusImage = when (item.documentStatus) {
                DocumentStatus.ACCEPTED -> R.drawable.ic_accepted
                DocumentStatus.IN_WAITING -> R.drawable.ic_waiting
                DocumentStatus.REJECTED -> R.drawable.ic_rejected
            }
            val statusText = when (item.documentStatus) {
                DocumentStatus.ACCEPTED -> {
                    val (day, month, year) = item.expirationDate
                    context.getString(R.string.portfolio_profile_expiration_date, day, month, year)
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

    interface OnDocumentItemClickListener {

        fun onDocumentItemClick(document: PortfolioDocument)
    }
}
