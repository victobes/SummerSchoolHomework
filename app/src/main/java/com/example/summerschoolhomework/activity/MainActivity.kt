package com.example.summerschoolhomework.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.summerschoolhomework.dialog.FilterDialogFragment
import com.example.summerschoolhomework.R
import com.example.summerschoolhomework.adapter.ItemsListAdapter
import com.example.summerschoolhomework.databinding.ActivityMainBinding
import com.example.summerschoolhomework.model.Item

class MainActivity : AppCompatActivity(), FilterDialogFragment.Callback{

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var itemsAdapter: ItemsListAdapter
    private var itemsList: List<Item> = emptyList()
    private var stateAllHeader = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        Log.d("snail", stateAllHeader.toString())
        itemsList = savedInstanceState?.getParcelableArrayList(KEY_LIST)
            ?: listOf(
                Item.StudentInfo(
                    getString(R.string.student_info_name),
                    getString(R.string.student_info_form),
                    getString(
                        R.string.student_info_github
                    )
                ),
                Item.ProjectInfo(getString(R.string.project_info_description)),
                Item.HeaderSkills(getString(R.string.header_skills), stateAllHeader),
                Item.SkillInfo(getString(R.string.skill_info_kotlin), 1.5f),
                Item.SkillInfo(getString(R.string.skill_info_java), 2f),
                Item.SkillInfo(getString(R.string.skill_info_python), 0.7f),
                Item.SkillInfo(getString(R.string.skill_info_pascal), 3.5f)
            )

        itemsAdapter = ItemsListAdapter()
        itemsAdapter.submitList(itemsList)
        itemsAdapter.notifyDataSetChanged()

        with(viewBinding.mainRecycler) {
            layoutManager = LinearLayoutManager(context)
            adapter = itemsAdapter
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(KEY_LIST, ArrayList(itemsList))
    }

    companion object {
        private const val KEY_LIST = "helper"
    }

    override fun FilterIsUsed(stateAll:Boolean) {
        stateAllHeader = stateAll
    }
}
