package id.learn.android.proyekacademy.data.source

import androidx.lifecycle.LiveData
import id.learn.android.proyekacademy.data.CourseEntity
import id.learn.android.proyekacademy.data.ModuleEntity

interface AcademyDataSource{
    fun getAllCourses(): LiveData<List<CourseEntity>>

    fun getBookmarkedCourses(): LiveData<List<CourseEntity>>

    fun getCourseWithModules(courseId: String): LiveData<CourseEntity>

    fun getAllModulesByCourse(courseId: String): LiveData<List<ModuleEntity>>

    fun getContent(courseId: String, moduleId: String): LiveData<ModuleEntity>
}