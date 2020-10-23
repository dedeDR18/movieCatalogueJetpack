package id.learn.android.proyekacademy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.learn.android.proyekacademy.data.CourseEntity
import id.learn.android.proyekacademy.data.ModuleEntity
import id.learn.android.proyekacademy.data.source.AcademyRepository
import id.learn.android.proyekacademy.utils.DataDummy

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): LiveData<CourseEntity> = academyRepository.getCourseWithModules(courseId)

    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)
}