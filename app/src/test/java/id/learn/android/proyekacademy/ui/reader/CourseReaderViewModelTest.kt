package id.learn.android.proyekacademy.ui.reader

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import id.learn.android.proyekacademy.data.source.local.entity.ContentEntity
import id.learn.android.proyekacademy.data.source.local.entity.ModuleEntity
import id.learn.android.proyekacademy.data.source.AcademyRepository
import id.learn.android.proyekacademy.utils.DataDummy
import id.learn.android.proyekacademy.vo.Resource
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class CourseReaderViewModelTest {

    private lateinit var viewModel: CourseReaderViewModel

    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId = dummyCourse.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)
    private val moduleId = dummyModules[0].moduleId

    @Mock
    private lateinit var academyRepository: AcademyRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = CourseReaderViewModel(academyRepository)
        viewModel.setCourseId(courseId)
        viewModel.setSelectedModule(moduleId)

        val dummyModule = dummyModules[0]
        dummyModule.contentEntity = ContentEntity("<h3 class=\\\"fr-text-bordered\\\">"+dummyModule.title+"</h3><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>")
    }

    @Mock
    private lateinit var modulesObserver: Observer<Resource<List<ModuleEntity>>>

    @Mock
    private lateinit var moduleObserver: Observer<Resource<ModuleEntity>>

    @Test
    fun setSelectedCourse() {
    }

    @Test
    fun setSelectedModule() {
    }

    @Test
    fun getModules() {
//        val modules = MutableLiveData<Resource<List<ModuleEntity>>>()
//        val resource = Resource.success(dummyModules)
//        modules.value = resource
//
//        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(modules)
//        val moduleEntities = viewModel.getModules().value
//        verify<AcademyRepository>(academyRepository).getAllModulesByCourse(courseId)
//        assertNotNull(moduleEntities)
//        assertEquals(7, moduleEntities?.size)
//
//        viewModel.getModules().observeForever(modulesObserver)
//        verify(modulesObserver).onChanged(dummyModules)

        val modules = MutableLiveData<Resource<List<ModuleEntity>>>()
        val resource = Resource.success(dummyModules) as Resource<List<ModuleEntity>>
        modules.value = resource
        `when`(academyRepository.getAllModulesByCourse(courseId)).thenReturn(modules)

        val observer = mock(Observer::class.java) as Observer<Resource<List<ModuleEntity>>>
        viewModel.modules.observeForever(observer)
        verify(observer).onChanged(resource)
    }

    @Test
    fun getSelectedModule() {
//        val module = MutableLiveData<ModuleEntity>()
//        module.value = dummyModules[0]
//
//        `when`(academyRepository.getContent(courseId, moduleId)).thenReturn(module)
//        val moduleEntity = viewModel.getSelectedModule().value as ModuleEntity
//        verify(academyRepository).getContent(courseId, moduleId)
//        assertNotNull(moduleEntity)
//        val contentEntity = moduleEntity.contentEntity
//        assertNotNull(contentEntity)
//        val content = contentEntity?.content
//        assertNotNull(content)
//        assertEquals(content, dummyModules[0].contentEntity?.content)
//
//        viewModel.getSelectedModule().observeForever(moduleObserver)
//        verify<Observer<ModuleEntity>>(moduleObserver).onChanged(dummyModules[0])

        val module = MutableLiveData<Resource<ModuleEntity>>()
        val resource = Resource.success(dummyModules[0])
        module.value = resource
        `when`(academyRepository.getContent(moduleId)).thenReturn(module)

        val observer = mock(Observer::class.java) as Observer<Resource<ModuleEntity>>
        viewModel.selectedModule.observeForever(observer)
        verify(observer).onChanged(resource)
    }
}