package id.learn.android.proyekacademy.ui.reader.content

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.learn.android.proyekacademy.R
import id.learn.android.proyekacademy.data.source.local.entity.ModuleEntity
import id.learn.android.proyekacademy.ui.reader.CourseReaderViewModel
import id.learn.android.proyekacademy.viewmodel.ViewModelFactory
import id.learn.android.proyekacademy.vo.Status
import kotlinx.android.synthetic.main.fragment_module_content.*


class ModuleContentFragment : Fragment() {
    companion object {
        val TAG = ModuleContentFragment::class.java.simpleName

        fun newInstance(): ModuleContentFragment {
            return ModuleContentFragment()
        }
    }

    private lateinit var viewModel: CourseReaderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_module_content, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            viewModel = ViewModelProvider(requireActivity(), factory)[CourseReaderViewModel::class.java]

            viewModel.selectedModule.observe(requireActivity(), Observer{ moduleEntity ->
                if (moduleEntity != null) {
                    when (moduleEntity.status) {
                        Status.LOADING -> progress_bar.visibility = View.VISIBLE
                        Status.SUCCESS -> if (moduleEntity.data != null) {
                            progress_bar.visibility = View.GONE
                            if (moduleEntity.data.contentEntity != null) {
                                populateWebView(moduleEntity.data)
                            }
                            setButtonNextPrevState(moduleEntity.data)
                            if (!moduleEntity.data.read) {
                                viewModel.readContent(moduleEntity.data)
                            }
                        }
                        Status.ERROR -> {
                            progress_bar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                    btn_next.setOnClickListener { viewModel.setNextPage() }
                    btn_prev.setOnClickListener { viewModel.setPrevPage() }
                }
            })
        }
    }

    private fun populateWebView(module: ModuleEntity) {
        module.contentEntity?.content?.let { web_view.loadData(it, "text/html", "UTF-8") }
    }

    private fun setButtonNextPrevState(module: ModuleEntity) {
        if (activity != null) {
            when (module.position) {
                0 -> {
                    btn_prev.isEnabled = false
                    btn_next.isEnabled = true
                }
                viewModel.getModuleSize() - 1 -> {
                    btn_prev.isEnabled = true
                    btn_next.isEnabled = false
                }
                else -> {
                    btn_prev.isEnabled = true
                    btn_next.isEnabled = true
                }
            }
        }
    }

}