package com.bhavya.exp03

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class DetailFragment : Fragment(R.layout.fragment_detail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<TextView>(R.id.tvTitle)
        val description = view.findViewById<TextView>(R.id.tvDescription)

        val course = arguments?.getString("course") ?: ""

        title.text = course

        description.text = when (course) {
            "Android" ->
                "Android is an operating system developed by Google for mobile devices."

            "Kotlin" ->
                "Kotlin is Google's preferred programming language for Android development."

            "Java" ->
                "Java is an object-oriented programming language widely used in Android."

            "Flutter" ->
                "Flutter is Google's UI toolkit for building cross-platform applications."

            "Python" ->
                "Python is a versatile programming language used in AI, automation, and web development."

            else ->
                "No description available."
        }
    }
}