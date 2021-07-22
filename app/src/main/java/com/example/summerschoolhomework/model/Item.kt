package com.example.summerschoolhomework.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed interface Item : Parcelable {
    @Parcelize
    data class StudentInfo(
        val studentName: String,
        val studentForm: String,
        val studentGithub: String
    ) : Item

    @Parcelize
    data class ProjectInfo(val projectDescription: String) : Item

    @Parcelize
    data class HeaderSkills(val headerSkills: String) : Item

    @Parcelize
    data class SkillInfo(val studentSkill: String, val studentExperience: Float) : Item
}
