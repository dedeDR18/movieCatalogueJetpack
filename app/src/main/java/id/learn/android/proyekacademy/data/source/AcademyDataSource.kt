package id.learn.android.proyekacademy.data.source

import id.learn.android.proyekacademy.data.CourseEntity
import id.learn.android.proyekacademy.data.ModuleEntity

interface AcademyDataSource{
    fun getAllCourses(): List<CourseEntity>

    fun getBookmarkedCourses(): List<CourseEntity>

    fun getCourseWithModules(courseId: String): CourseEntity

    fun getAllModulesByCourse(courseId: String): List<ModuleEntity>

    fun getContent(courseId: String, moduleId: String): ModuleEntity
}