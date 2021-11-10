package com.example.lesson_6_lukin.thirdScreen

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.lesson_6_lukin.R

class PagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    companion object {
        val TEXT = listOf("Картинка 1", "Картинка 2", "Картинка 3")
        val CATS = listOf(R.drawable.cat1, R.drawable.cat2, R.drawable.cat3)
    }

    override fun getItemCount() = CATS.size

    override fun createFragment(position: Int): Fragment {
        return SubFragment.newInstance(TEXT[position], CATS[position])
    }
}