package id.learn.android.proyekacademy.ui.bookmark

import id.learn.android.proyekacademy.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
