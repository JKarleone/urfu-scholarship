package ru.intelligency.scholarship.data.portfolio

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "documents")
data class DocumentEntity(
    @PrimaryKey(autoGenerate = true)
    val documentId: Long = 0,
    @ColumnInfo(name = "name")
    val name: String,
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
