package ru.intelligency.scholarship.data.myapplications

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.intelligency.scholarship.domain.myapplications.ApplicationsRepository
import ru.intelligency.scholarship.domain.myapplications.models.Application
import ru.intelligency.scholarship.presentation.utils.Status
import java.util.Date

class ApplicationsRepositoryImpl : ApplicationsRepository {

    private val applications = mutableListOf(
        Application(
            id = 0,
            scholarshipType = "Стипендия Правительства РФ по приоритетным направлениям",
            fullName = "Пупкин Вася Олегович",
            academicGroupNumber = "РИ-480022",
            specialityCode = "09.03.04",
            specialityName = "Программная инженерия",
            totalMarksCount = 25,
            excellentMarksCount = 1,
            applicationStatus = Status.IN_WAITING,
            sendingDate = Date().time
        ),
        Application(
            id = 1,
            scholarshipType = "Стипендия Правительства РФ по приоритетным направлениям",
            fullName = "Пупкин Вася Олегович",
            academicGroupNumber = "РИ-480022",
            specialityCode = "09.03.04",
            specialityName = "Программная инженерия",
            totalMarksCount = 25,
            excellentMarksCount = 1,
            applicationStatus = Status.REJECTED,
            sendingDate = Date().time
        ),
        Application(
            id = 2,
            scholarshipType = "Стипендия Правительства РФ по приоритетным направлениям",
            fullName = "Пупкин Вася Олегович",
            academicGroupNumber = "РИ-480022",
            specialityCode = "09.03.04",
            specialityName = "Программная инженерия",
            totalMarksCount = 25,
            excellentMarksCount = 1,
            applicationStatus = Status.ACCEPTED,
            sendingDate = Date().time
        ),
    )

    override fun getApplications(): Flow<List<Application>> {
        return flow {
            emit(applications)
        }
    }

    override fun getApplicationById(id: Long): Flow<Application> {
        return flow {
            emit(applications.first { it.id == id })
        }
    }

    override fun saveApplication(application: Application) {
        applications.add(application)
    }

    override fun deleteApplication(applicationId: Long) {
        applications.first { it.id == applicationId }.apply {
            applications.remove(this)
        }
    }
}
