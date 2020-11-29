package id.learn.android.proyekacademy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.learn.android.proyekacademy.data.source.local.entity.CourseEntity
import id.learn.android.proyekacademy.data.source.AcademyRepository
import id.learn.android.proyekacademy.vo.Resource

class AcademyViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    //fun getCourses(): LiveData<List<CourseEntity>> = academyRepository.getAllCourses()
    fun getCourses(): LiveData<Resource<List<CourseEntity>>> = academyRepository.getAllCourses()
}