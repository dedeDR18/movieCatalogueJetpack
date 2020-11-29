package id.learn.android.proyekacademy.data.source

import androidx.lifecycle.LiveData
import id.learn.android.proyekacademy.data.source.local.entity.CourseEntity
import id.learn.android.proyekacademy.data.source.local.entity.CourseWithModule
import id.learn.android.proyekacademy.data.source.local.entity.ModuleEntity
import id.learn.android.proyekacademy.vo.Resource

interface AcademyDataSource{
    fun getAllCourses(): LiveData<Resource<List<CourseEntity>>>

    fun getCourseWithModules(courseId: String): LiveData<Resource<CourseWithModule>>

    fun getAllModulesByCourse(courseId: String): LiveData<Resource<List<ModuleEntity>>>

    fun getContent(moduleId: String): LiveData<Resource<ModuleEntity>>

    fun getBookmarkedCourses(): LiveData<List<CourseEntity>>

    fun setCourseBookmark(course: CourseEntity, state: Boolean)
    fun setReadModule(module: ModuleEntity)
}