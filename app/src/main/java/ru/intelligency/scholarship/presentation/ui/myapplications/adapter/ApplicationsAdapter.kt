package ru.intelligency.scholarship.presentation.ui.myapplications.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.intelligency.scholarship.R
import ru.intelligency.scholarship.databinding.ItemApplicationBinding
import ru.intelligency.scholarship.domain.myapplications.models.Application
import ru.intelligency.scholarship.presentation.extensions.getStringDate
import ru.intelligency.scholarship.presentation.extensions.setStatusIcon

class ApplicationsAdapter(
    private var applications: List<Application>,
    private val clickListener: OnApplicationItemClickListener
) : RecyclerView.Adapter<ApplicationsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemApplicationBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentApplication = applications[position]
        holder.apply {
            bind(currentApplication)
            itemView.setOnClickListener {
                clickListener.onApplicationItemClick(currentApplication)
            }
        }
    }

    override fun getItemCount(): Int {
        return applications.size
    }

    fun submitData(list: List<Application>) {
        this.applications = list
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ItemApplicationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Application) {
            with(binding) {
                applicationTitle.text = item.scholarshipType
                applicationStatusIcon.setStatusIcon(item.applicationStatus)
                applicationStatusText.text = root.context.getString(
                    R.string.application_status_text,
                    item.sendingDate.getStringDate()
                )
            }
        }
    }

    interface OnApplicationItemClickListener {
        fun onApplicationItemClick(application: Application)
    }
}
