package id.learn.android.proyekacademy.ui.reader

interface CourseReaderCallback {
    fun moveTo(position: Int, moduleId: String)
}