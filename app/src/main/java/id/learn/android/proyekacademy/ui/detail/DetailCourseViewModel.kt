package id.learn.android.proyekacademy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import id.learn.android.proyekacademy.data.source.local.entity.CourseEntity
import id.learn.android.proyekacademy.data.source.local.entity.ModuleEntity
import id.learn.android.proyekacademy.data.source.AcademyRepository
import id.learn.android.proyekacademy.data.source.local.entity.CourseWithModule
import id.learn.android.proyekacademy.vo.Resource

class DetailCourseViewModel(private val academyRepository: AcademyRepository) : ViewModel() {
//    private lateinit var courseId: String
//
//    fun setSelectedCourse(courseId: String) {
//        this.courseId = courseId
//    }
//
//    fun getCourse(): LiveData<CourseEntity> = academyRepository.getCourseWithModules(courseId)
//
//    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)
    val courseId = MutableLiveData<String>()

    fun setCourseId(courseId: String) {
        this.courseId.value = courseId
    }

    var courseModule: LiveData<Resource<CourseWithModule>> = Transformations.switchMap(courseId) { mCourseId ->
        academyRepository.getCourseWithModules(mCourseId)
    }

    fun setBookmark() {
        val moduleResource = courseModule.value
        if (moduleResource != null) {
            val courseWithModule = moduleResource.data
            if (courseWithModule != null) {
                val courseEntity = courseWithModule.mCourse
                val newState = !courseEntity.bookmarked
                academyRepository.setCourseBookmark(courseEntity, newState)
            }
        }
    }
}