package id.learn.android.proyekacademy.ui.bookmark

import id.learn.android.proyekacademy.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
