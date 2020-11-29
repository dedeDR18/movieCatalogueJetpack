package id.learn.android.proyekacademy.data.source.remote

import android.os.Handler
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.learn.android.proyekacademy.data.source.remote.response.ContentResponse
import id.learn.android.proyekacademy.data.source.remote.response.CourseResponse
import id.learn.android.proyekacademy.data.source.remote.response.ModuleResponse
import id.learn.android.proyekacademy.utils.EspressoIdlingResource
import id.learn.android.proyekacademy.utils.JsonHelper


class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler()

    companion object {

        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllCourses(): LiveData<ApiResponse<List<CourseResponse>>> {
        EspressoIdlingResource.increment()
        val resultCourse = MutableLiveData<ApiResponse<List<CourseResponse>>>()
        handler.postDelayed({
            resultCourse.value = ApiResponse.success(jsonHelper.loadCourses())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultCourse
    }

    fun getModules(courseId: String): LiveData<ApiResponse<List<ModuleResponse>>> {
        EspressoIdlingResource.increment()
        val resultModules = MutableLiveData<ApiResponse<List<ModuleResponse>>>()
        handler.postDelayed({
            resultModules.value = ApiResponse.success(jsonHelper.loadModule(courseId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultModules
    }

    fun getContent(moduleId: String): LiveData<ApiResponse<ContentResponse>> {
        EspressoIdlingResource.increment()
        val resultContent = MutableLiveData<ApiResponse<ContentResponse>>()
        handler.postDelayed({
            resultContent.value = ApiResponse.success(jsonHelper.loadContent(moduleId))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return resultContent
    }

}