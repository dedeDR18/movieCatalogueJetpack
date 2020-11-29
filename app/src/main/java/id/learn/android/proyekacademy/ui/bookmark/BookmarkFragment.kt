package id.learn.android.proyekacademy.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.learn.android.proyekacademy.R
import id.learn.android.proyekacademy.data.source.local.entity.CourseEntity
import id.learn.android.proyekacademy.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_bookmark.*

class BookmarkFragment : Fragment(), BookmarkFragmentCallback {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireContext())
            val viewModel = ViewModelProvider(this, factory)[BookmarkViewModel::class.java]

            val adapter = BookmarkAdapter(this)
            progress_bar.visibility = View.VISIBLE
            viewModel.getBookmarks().observe(requireActivity(), Observer{ courses ->
                progress_bar.visibility = View.GONE
                adapter.setCourses(courses)
                adapter.notifyDataSetChanged()
            })

            with(rv_bookmark) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
        }
    }
    override fun onShareClick(course: CourseEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder.from(requireActivity()).apply {
                setType(mimeType)
                setChooserTitle("Bagikan aplikasi ini sekarang.")
                setText(resources.getString(R.string.share_text, course.title))
                startChooser()
            }
        }
    }

}