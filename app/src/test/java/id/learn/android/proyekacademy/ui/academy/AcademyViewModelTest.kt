package id.learn.android.proyekacademy.ui.academy

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.learn.android.proyekacademy.data.source.local.entity.CourseEntity
import id.learn.android.proyekacademy.data.source.AcademyRepository
import id.learn.android.proyekacademy.utils.DataDummy
import id.learn.android.proyekacademy.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class AcademyViewModelTest {

    private lateinit var viewModel:AcademyViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var observer: Observer<Resource<List<CourseEntity>>>

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = AcademyViewModel(academyRepository)
    }

    @Test
    fun getCourses() {
        val dummyCourses = Resource.success(DataDummy.generateDummyCourses())
        val courses = MutableLiveData<Resource<List<CourseEntity>>>()
        courses.value = dummyCourses

        `when`(academyRepository.getAllCourses()).thenReturn(courses)
        val courseEntities = viewModel.getCourses().value?.data
        verify<AcademyRepository>(academyRepository).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(5, courseEntities?.size)

        viewModel.getCourses().observeForever(observer)
        verify(observer).onChanged(dummyCourses)
    }
}