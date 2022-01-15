package ru.intelligency.scholarship.data.portfolio

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.intelligency.scholarship.presentation.utils.Status

@Entity(tableName = "documents")
data class DocumentEntity(
    @PrimaryKey
    val documentId: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "document_status")
    val documentStatus: Status,
    @ColumnInfo(name = "event_type")
    val eventType: String,
    @ColumnInfo(name = "event_status")
    val eventStatus: String,
    @ColumnInfo(name = "date_of_receipt")
    val dateOfReceipt: Long,
    @ColumnInfo(name = "event_place")
    val eventPlace: String,
    @ColumnInfo(name = "file_name")
    val fileName: String
)
