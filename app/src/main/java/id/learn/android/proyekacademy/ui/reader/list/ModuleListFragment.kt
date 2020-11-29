package id.learn.android.proyekacademy.ui.reader.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.learn.android.proyekacademy.R
import id.learn.android.proyekacademy.data.source.local.entity.ModuleEntity
import id.learn.android.proyekacademy.ui.reader.CourseReaderActivity
import id.learn.android.proyekacademy.ui.reader.CourseReaderCallback
import id.learn.android.proyekacademy.ui.reader.CourseReaderViewModel
import id.learn.android.proyekacademy.viewmodel.ViewModelFactory
import id.learn.android.proyekacademy.vo.Status
import kotlinx.android.synthetic.main.fragment_module_list.*


class ModuleListFragment : Fragment(), MyAdapterClickListener {

    companion object {
        val TAG = ModuleListFragment::class.java.simpleName

        fun newInstance(): ModuleListFragment = ModuleListFragment()
    }

    private lateinit var adapter: ModuleListAdapter
    private lateinit var courseReaderCallback: CourseReaderCallback
    private lateinit var viewModel: CourseReaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_module_list, container, false)


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ViewModelFactory.getInstance(requireContext())
        viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]
        adapter = ModuleListAdapter(this)

        progress_bar.visibility = View.VISIBLE
        viewModel.modules.observe(requireActivity(), Observer{ moduleEntities ->
            if (moduleEntities != null) {
                when (moduleEntities.status) {
                    Status.LOADING -> progress_bar.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        populateRecyclerView(moduleEntities.data as List<ModuleEntity>)
                    }
                    Status.ERROR -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        courseReaderCallback = context as CourseReaderActivity
    }

    override fun onItemClicked(position: Int, moduleId: String) {
        courseReaderCallback.moveTo(position, moduleId)
        viewModel.setSelectedModule(moduleId)
    }

    private fun populateRecyclerView(modules: List<ModuleEntity>) {
        progress_bar.visibility = View.GONE
        adapter.setModules(modules)
        rv_module.layoutManager = LinearLayoutManager(context)
        rv_module.setHasFixedSize(true)
        rv_module.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(rv_module.context, DividerItemDecoration.VERTICAL)
        rv_module.addItemDecoration(dividerItemDecoration)
    }


}