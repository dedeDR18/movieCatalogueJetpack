package id.learn.android.proyekacademy.data.source.remote

import id.learn.android.proyekacademy.data.source.remote.response.ContentResponse
import id.learn.android.proyekacademy.data.source.remote.response.CourseResponse
import id.learn.android.proyekacademy.data.source.remote.response.ModuleResponse
import id.learn.android.proyekacademy.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllCourses(): List<CourseResponse> = jsonHelper.loadCourses()

    fun getModules(courseId: String): List<ModuleResponse> = jsonHelper.loadModule(courseId)

    fun getContent(moduleId: String): ContentResponse = jsonHelper.loadContent(moduleId)

}