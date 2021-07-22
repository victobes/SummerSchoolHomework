package com.example.summerschoolhomework.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.summerschoolhomework.R
import com.example.summerschoolhomework.databinding.ItemHeaderSkillsBinding
import com.example.summerschoolhomework.databinding.ItemProjectInfoBinding
import com.example.summerschoolhomework.databinding.ItemStudentInfoBinding
import com.example.summerschoolhomework.databinding.ItemStudentSkillBinding
import com.example.summerschoolhomework.model.Item
import com.example.summerschoolhomework.util.timeConverter

class ItemsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<Item> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            VIEW_TYPE_STUDENT_INFO ->
                ViewHolderStudentInfo(
                    ItemStudentInfoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            VIEW_TYPE_PROJECT_INFO ->
                ViewHolderProjectInfo(
                    ItemProjectInfoBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            VIEW_TYPE_HEADER_SKILLS ->
                ViewHolderHeaderSkills(
                    ItemHeaderSkillsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )

            VIEW_TYPE_STUDENT_SKILL ->
                ViewHolderStudentSkill(
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
        when (val item = items[position]) {
            is Item.StudentInfo ->
                (holder as? ViewHolderStudentInfo)?.onBind(item)

            is Item.ProjectInfo ->
                (holder as? ViewHolderProjectInfo)?.onBind(item)

            is Item.HeaderSkills ->
                (holder as? ViewHolderHeaderSkills)?.onBind(item)

            is Item.SkillInfo ->
                (holder as? ViewHolderStudentSkill)?.onBind(item)
        }
    }

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is Item.StudentInfo ->
                VIEW_TYPE_STUDENT_INFO

            is Item.ProjectInfo ->
                VIEW_TYPE_PROJECT_INFO

            is Item.HeaderSkills ->
                VIEW_TYPE_HEADER_SKILLS

            is Item.SkillInfo ->
                VIEW_TYPE_STUDENT_SKILL
        }

    override fun getItemCount(): Int =
        items.size

    class ViewHolderStudentInfo(
        private val viewBinding: ItemStudentInfoBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(item: Item.StudentInfo) {
            viewBinding.studentName.text = item.studentName
            viewBinding.studentForm.text = item.studentForm
            viewBinding.studentImage.setImageResource(R.drawable.student_image)
            viewBinding.studentGithubButton.setOnClickListener {
                viewBinding.root.context.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(item.studentGithub)
                    )
                )
            }
        }
    }

    class ViewHolderProjectInfo(
        private val viewBinding: ItemProjectInfoBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(item: Item.ProjectInfo) {
            viewBinding.projectDescription.text = item.projectDescription
        }
    }

    class ViewHolderHeaderSkills(
        private val viewBinding: ItemHeaderSkillsBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(item: Item.HeaderSkills) {
            viewBinding.headerSkills.text = item.headerSkills
        }
    }

    class ViewHolderStudentSkill(
        private val viewBinding: ItemStudentSkillBinding
    ) : RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(item: Item.SkillInfo) {
            viewBinding.studentSkill.text = item.studentSkill
            viewBinding.studentExperience.text = timeConverter(item.studentExperience)
        }
    }

    companion object {
        const val VIEW_TYPE_STUDENT_INFO = 1
        const val VIEW_TYPE_PROJECT_INFO = 2
        const val VIEW_TYPE_HEADER_SKILLS = 3
        const val VIEW_TYPE_STUDENT_SKILL = 4
    }
}
