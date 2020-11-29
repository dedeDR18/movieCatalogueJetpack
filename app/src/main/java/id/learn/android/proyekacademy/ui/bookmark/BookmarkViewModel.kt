package id.learn.android.proyekacademy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.learn.android.proyekacademy.data.source.local.entity.CourseEntity
import id.learn.android.proyekacademy.data.source.AcademyRepository

class BookmarkViewModel(private val academyRepository: AcademyRepository) : ViewModel() {

    fun getBookmarks(): LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCourses()
}