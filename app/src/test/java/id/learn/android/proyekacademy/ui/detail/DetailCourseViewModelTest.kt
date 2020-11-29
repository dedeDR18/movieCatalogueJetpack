package id.learn.android.proyekacademy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.learn.android.proyekacademy.data.source.local.entity.CourseEntity
import id.learn.android.proyekacademy.data.source.local.entity.ModuleEntity
import id.learn.android.proyekacademy.data.source.AcademyRepository
import id.learn.android.proyekacademy.data.source.local.entity.CourseWithModule
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

class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Mock
    private lateinit var courseObserver: Observer<CourseEntity>

    @Mock
    private lateinit var modulesObserver: Observer<List<ModuleEntity>>

    @Mock
    private lateinit var observer: Observer<Resource<CourseWithModule>>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = DetailCourseViewModel(academyRepository)
        viewModel.setCourseId(courseId)
    }


    @Test
    fun getCourseWithModule() {
        val dummyCourseWithModule = Resource.success(DataDummy.generateDummyCourseWithModules(dummyCourse, true))
        val course = MutableLiveData<Resource<CourseWithModule>>()
        course.value = dummyCourseWithModule
        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(course)
        viewModel.courseModule.observeForever(observer)
        verify(observer).onChanged(dummyCourseWithModule)
    }

//    @Test
//    fun getCourse() {
//        val course = MutableLiveData<CourseEntity>()
//        course.value = dummyCourse
//
//        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(course)
//        val courseEntity = viewModel.getCourse().value as CourseEntity
//        verify(academyRepository).getCourseWithModules(courseId)
//        assertNotNull(courseEntity)
//        assertEquals(dummyCourse.courseId, courseEntity.courseId)
//        assertEquals(dummyCourse.deadline, courseEntity.deadline)
//        assertEquals(dummyCourse.description, courseEntity.description)
//        assertEquals(dummyCourse.imagePath, courseEntity.imagePath)
//        assertEquals(dummyCourse.title, courseEntity.title)
//
//        viewModel.getCourse().observeForever(courseObserver)
//        verify(courseObserver).onChanged(dummyCourse)
//    }

//    @Test
//    fun getModules() {
//        val module = MutableLiveData<List<ModuleEntity>>()
//        module.value = dummyModules
//
//        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(module)
//        val moduleEntities = viewModel.getModules().value
//        verify<AcademyRepository>(academyRepository).getAllModulesByCourse(courseId)
//        assertNotNull(moduleEntities)
//        assertEquals(7, moduleEntities?.size)
//
//        viewModel.getModules().observeForever(modulesObserver)
//        verify(modulesObserver).onChanged(dummyModules)
//    }
}