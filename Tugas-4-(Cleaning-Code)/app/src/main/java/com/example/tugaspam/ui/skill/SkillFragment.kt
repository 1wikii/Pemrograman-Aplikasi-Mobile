package com.example.tugaspam.ui.skill

import android.content.res.TypedArray
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugaspam.R
import java.util.Locale
import kotlin.collections.ArrayList
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SkillFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter : MyAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var skillArrayList : ArrayList<Skill>
    private lateinit var searchView: SearchView
    private lateinit var imageId : TypedArray
    private lateinit var heading : Array<String>
    lateinit var skill : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_skill, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataInitialize()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MyAdapter(skillArrayList)
        recyclerView.adapter = adapter
        searchView = view.findViewById(R.id.search_action)

        adapter.onItemClick = {
            navigateToDetail(it.heading)
        }


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Handler(Looper.getMainLooper()).removeCallbacksAndMessages(null)
                Handler(Looper.getMainLooper()).postDelayed({
                    filterList(newText)
                }, 1250)

                return false
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        param1 = null
    }

    private fun navigateToDetail(extraName: String){
        findNavController().navigate(SkillFragmentDirections.actionNavSkillToSkillDetail(extraName))
    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<Skill>()
            for (i in skillArrayList) {
                if (i.heading.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show()
            }
            else {
                adapter.setFilteredList(filteredList)
            }
        }
    }


    private fun dataInitialize(){
        skillArrayList = arrayListOf<Skill>()
        imageId = resources.obtainTypedArray(R.array.integer_skill_array)
        heading = resources.getStringArray(R.array.string_skill_array)

        for (i in 0..<imageId.length()) {
            val skill = Skill(imageId.getResourceId(i,0), heading[i])
            skillArrayList.add(skill)
        }

        imageId.recycle()

    }



}