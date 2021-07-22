package com.example.summerschoolhomework.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.summerschoolhomework.databinding.ItemHeaderSkillsBinding
import com.example.summerschoolhomework.databinding.ItemProjectInfoBinding
import com.example.summerschoolhomework.databinding.ItemStudentInfoBinding
import com.example.summerschoolhomework.databinding.ItemStudentSkillBinding
import com.example.summerschoolhomework.model.Item

class ItemsListAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(ItemDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            ItemsAdapter.VIEW_TYPE_STUDENT_INFO ->
                ItemsAdapter.ViewHolderStudentInfo(
                    ItemStudentInfoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            ItemsAdapter.VIEW_TYPE_PROJECT_INFO ->
                ItemsAdapter.ViewHolderProjectInfo(
                    ItemProjectInfoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            ItemsAdapter.VIEW_TYPE_HEADER_SKILLS ->
                ItemsAdapter.ViewHolderHeaderSkills(
                    ItemHeaderSkillsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            ItemsAdapter.VIEW_TYPE_STUDENT_SKILL ->
                ItemsAdapter.ViewHolderStudentSkill(
                    ItemStudentSkillBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            else ->
                throw IllegalArgumentException("")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (val item = getItem(position)) {
            is Item.StudentInfo ->
                (holder as? ItemsAdapter.ViewHolderStudentInfo)?.onBind(item)

            is Item.ProjectInfo ->
                (holder as? ItemsAdapter.ViewHolderProjectInfo)?.onBind(item)

            is Item.HeaderSkills ->
                (holder as? ItemsAdapter.ViewHolderHeaderSkills)?.onBind(item)

            is Item.SkillInfo ->
                (holder as? ItemsAdapter.ViewHolderStudentSkill)?.onBind(item)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (getItem(position)) {
            is Item.StudentInfo ->
                ItemsAdapter.VIEW_TYPE_STUDENT_INFO

            is Item.ProjectInfo ->
                ItemsAdapter.VIEW_TYPE_PROJECT_INFO

            is Item.HeaderSkills ->
                ItemsAdapter.VIEW_TYPE_HEADER_SKILLS

            is Item.SkillInfo ->
                ItemsAdapter.VIEW_TYPE_STUDENT_SKILL
        }
}

object ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem is Item.StudentInfo && newItem is Item.StudentInfo || oldItem is Item.ProjectInfo && newItem is Item.ProjectInfo || oldItem is Item.HeaderSkills && newItem is Item.HeaderSkills || oldItem is Item.SkillInfo && newItem is Item.SkillInfo

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem == newItem
}
