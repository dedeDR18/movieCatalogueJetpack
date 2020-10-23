package id.learn.android.proyekacademy.data.source.remote

import android.os.Handler
import id.learn.android.proyekacademy.data.source.remote.response.ContentResponse
import id.learn.android.proyekacademy.data.source.remote.response.CourseResponse
import id.learn.android.proyekacademy.data.source.remote.response.ModuleResponse
import id.learn.android.proyekacademy.utils.JsonHelper


class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler()

    companion object {

        private const val SERVICE_LATENCEY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllCourses(callback: LoadCoursesCallback){
        handler.postDelayed({
            callback.onAllCoursesReceived(jsonHelper.loadCourses())
        }, SERVICE_LATENCEY_IN_MILLIS)
    }

    fun getModules(courseId: String, callback: LoadModulesCallback){
        handler.postDelayed({
            callback.onAllModulesReceived(jsonHelper.loadModule(courseId))
        }, SERVICE_LATENCEY_IN_MILLIS)
    }

    fun getContent(moduleId: String, callback: LoadContentCallback){
        handler.postDelayed({
            callback.onContentReceived(jsonHelper.loadContent(moduleId))
        }, SERVICE_LATENCEY_IN_MILLIS)
    }

    interface LoadCoursesCallback {
        fun onAllCoursesReceived(courseResponses: List<CourseResponse>)
    }
    interface LoadModulesCallback {
        fun onAllModulesReceived(moduleResponses: List<ModuleResponse>)
    }
    interface LoadContentCallback {
        fun onContentReceived(contentResponse: ContentResponse)
    }

}