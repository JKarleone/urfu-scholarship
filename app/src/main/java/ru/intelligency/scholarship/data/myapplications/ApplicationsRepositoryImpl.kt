package ru.intelligency.scholarship.data.myapplications

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.intelligency.scholarship.domain.myapplications.ApplicationsRepository
import ru.intelligency.scholarship.domain.myapplications.models.Application
import ru.intelligency.scholarship.domain.portfolio.model.SimpleDate
import ru.intelligency.scholarship.presentation.utils.Status

class ApplicationsRepositoryImpl : ApplicationsRepository {

    private val applications = listOf(
        Application(
            id = "0",
            scholarshipType = "Стипендия Правительства РФ по приоритетным направлениям",
            fullName = "Пупкин Вася Олегович",
            academicGroupNumber = "РИ-480022",
            specialityCode = "09.03.04",
            specialityName = "Программная инженерия",
            totalMarksCount = 25,
            excellentMarksCount = 1,
            applicationStatus = Status.IN_WAITING,
            sendingDate = SimpleDate(1, 12, 2021)
        ),
        Application(
            id = "1",
            scholarshipType = "Стипендия Правительства РФ по приоритетным направлениям",
            fullName = "Пупкин Вася Олегович",
            academicGroupNumber = "РИ-480022",
            specialityCode = "09.03.04",
            specialityName = "Программная инженерия",
            totalMarksCount = 25,
            excellentMarksCount = 1,
            applicationStatus = Status.REJECTED,
            sendingDate = SimpleDate(1, 12, 2021)
        ),
        Application(
            id = "2",
            scholarshipType = "Стипендия Правительства РФ по приоритетным направлениям",
            fullName = "Пупкин Вася Олегович",
            academicGroupNumber = "РИ-480022",
            specialityCode = "09.03.04",
            specialityName = "Программная инженерия",
            totalMarksCount = 25,
            excellentMarksCount = 1,
            applicationStatus = Status.ACCEPTED,
            sendingDate = SimpleDate(1, 12, 2021)
        ),

        )

    override fun getApplications(): Flow<List<Application>> {
        return flow {
            emit(applications)
        }
    }

    override fun getApplicationById(id: String): Flow<Application> {
        return flow {
            emit(applications.first { it.id == id })
        }
    }
}
