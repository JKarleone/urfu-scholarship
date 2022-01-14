package ru.intelligency.scholarship.presentation.ui.myapplications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.data.portfolio.ImageProvider
import ru.intelligency.scholarship.databinding.ItemPortfolioDocumentBinding
import ru.intelligency.scholarship.presentation.extensions.getStatusText
import ru.intelligency.scholarship.presentation.extensions.setStatusIcon

class DocumentSelectionAdapter(
    private var documents: List<ApplicationDocument>,
    private val imageProvider: ImageProvider
) : RecyclerView.Adapter<DocumentSelectionAdapter.ViewHolder>() {

    val selectedDocsIds = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPortfolioDocumentBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding, imageProvider)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(documents[position])
        holder.itemView.setOnClickListener {
            val currentDocument = documents[position]
            val isSelected = currentDocument.isSelected
            if (isSelected) {
                selectedDocsIds.remove(currentDocument.id)
            } else {
                selectedDocsIds.add(currentDocument.id)
            }
            documents[position].isSelected = !isSelected
            notifyItemChanged(position)
        }
    }

    override fun getItemCount(): Int {
        return documents.size
    }

    fun submitData(list: List<ApplicationDocument>) {
        documents = list
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemPortfolioDocumentBinding,
        private val imageProvider: ImageProvider,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ApplicationDocument) {
            val context = binding.root.context

            with(binding) {
                statusImageView.setStatusIcon(item.documentStatus)
                nameTextView.text = item.name
                descriptionTextView.text = item.description
                statusText.text = item.getStatusText(context)
                when {
                    item.isSelected -> {
                        portfolioDocumentImageView.setImageResource(R.drawable.ic_selected_document)
                    }
                    item.fileName.isNotEmpty() -> {
                        portfolioDocumentImageView.setImageBitmap(
                            imageProvider.getDocumentByName(
                                item.fileName
                            )
                        )
                    }
                    else -> {
                        portfolioDocumentImageView.setImageDrawable(null)
                    }
                }
            }
        }
    }
}
