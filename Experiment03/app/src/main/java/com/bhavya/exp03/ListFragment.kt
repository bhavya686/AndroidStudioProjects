package com.bhavya.exp03

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment

class ListFragment : Fragment(R.layout.fragment_list) {

    private val courseList = arrayOf(
        "Android",
        "Kotlin",
        "Java",
        "Flutter",
        "Python"
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listView = view.findViewById<ListView>(R.id.listViewCourses)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            courseList
        )

        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->

            val selectedCourse = courseList[position]

            val detailFragment = DetailFragment()

            val bundle = Bundle()
            bundle.putString("course", selectedCourse)

            detailFragment.arguments = bundle

            if (requireActivity().findViewById<View?>(R.id.detailContainer) != null) {

                parentFragmentManager.beginTransaction()
                    .replace(R.id.detailContainer, detailFragment)
                    .commit()

            } else {

                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, detailFragment)
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}