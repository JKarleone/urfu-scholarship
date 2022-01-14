package ru.intelligency.scholarship.presentation.ui.portfolio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.data.portfolio.ImageProvider
import ru.intelligency.scholarship.databinding.ItemPortfolioDocumentBinding
import ru.intelligency.scholarship.presentation.extensions.getStatusText
import ru.intelligency.scholarship.presentation.extensions.isExpired
import ru.intelligency.scholarship.presentation.extensions.setStatusIcon
import ru.intelligency.scholarship.presentation.ui.portfolio.model.PortfolioDocument

class DocumentsAdapter(
    private var documents: List<PortfolioDocument>,
    private val clickListener: OnDocumentItemClickListener?,
    private val imageProvider: ImageProvider
) : RecyclerView.Adapter<DocumentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPortfolioDocumentBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, imageProvider)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDocument = documents[position]
        holder.bind(currentDocument)
        holder.itemView.setOnClickListener {
            clickListener?.onDocumentItemClick(currentDocument)
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
        private val binding: ItemPortfolioDocumentBinding,
        private val imageProvider: ImageProvider
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PortfolioDocument) {
            val context = binding.root.context

            with(binding) {
                if (item.dateOfReceipt.isExpired()) {
                    statusImageView.setImageResource(R.drawable.ic_rejected)
                    statusText.setText(R.string.document_rejected)
                } else {
                    statusImageView.setStatusIcon(item.documentStatus)
                    statusText.text = item.getStatusText(context)
                }
                nameTextView.text = item.name
                descriptionTextView.text = item.description
                if (item.fileName.isNotEmpty()) {
                    portfolioDocumentImageView.setImageBitmap(imageProvider.getDocumentByName(item.fileName))
                }
            }
        }
    }

    interface OnDocumentItemClickListener {

        fun onDocumentItemClick(document: PortfolioDocument)
    }
}
