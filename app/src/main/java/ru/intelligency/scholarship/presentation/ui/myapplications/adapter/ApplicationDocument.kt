package ru.intelligency.scholarship.presentation.ui.myapplications.adapter

import ru.intelligency.scholarship.presentation.utils.Status
import java.util.Date

data class ApplicationDocument(
    val id: Long = 0,
    val name: String = "",
    val description: String = "",
    val documentStatus: Status = Status.IN_WAITING,
    val expirationDate: Long = Date().time,
    val fileName: String = "",
    var isSelected: Boolean = false
)
