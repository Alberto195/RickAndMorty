package com.example.myapplication.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R


class InfoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_info, container, false)
        // Inflate the layout for this fragment
        val status = arguments?.getString("status")
        val type = arguments?.getString("type")
        val gender = arguments?.getString("gender")
        val species = arguments?.getString("species")

        Log.i("InfoFragment", "species = $species")

        val speciesView = v.findViewById<TextView>(R.id.character_species)
        val statusView = v.findViewById<TextView>(R.id.character_status)
        val typeView = v.findViewById<TextView>(R.id.character_type)
        val genderView = v.findViewById<TextView>(R.id.character_gender)

        speciesView?.text = species
        statusView?.text = status
        typeView?.text = type
        genderView?.text = gender

        return v
    }
}