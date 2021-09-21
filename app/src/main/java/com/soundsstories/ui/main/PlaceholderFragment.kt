package com.soundsstories.ui.main

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.soundsstories.R
import com.soundsstories.SoundPage
import com.soundsstories.SoundTemplate
import com.soundsstories.databinding.FragmentMainBinding
import com.soundsstories.deserialize
import com.soundsstories.serialize
import java.time.YearMonth
import kotlin.jvm.internal.Reflection
import kotlin.reflect.KClass

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel
    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProvider(this).get(PageViewModel::class.java).apply {
            arguments?.getString(ARG_SECTION_NUMBER)?.let { setTitle(it.deserialize(SoundPage::class.java)) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val root = binding.root

//        val textView: TextView = binding.sectionLabel
//        pageViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

        pageViewModel.backgroundColor.observe(viewLifecycleOwner, {
            binding.root.setBackgroundColor(Color.parseColor(it))
        })
        pageViewModel.soundlist.observe(viewLifecycleOwner, {
            binding.rv.layoutManager = GridLayoutManager(context, 2)
            val photoAdapter = PhotoAdapter(context!!)

            binding.rv.adapter = photoAdapter
            photoAdapter.setDataList(it)
//            binding.root.setBackgroundColor(Color.parseColor(it))
        })

        return root
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: SoundPage): PlaceholderFragment {
            return PlaceholderFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_SECTION_NUMBER, sectionNumber.serialize())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}