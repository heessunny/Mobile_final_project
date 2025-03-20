package com.example.a01_20210778

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class StudyVpAdapter (activity: FragmentActivity) : FragmentStateAdapter(activity)  {
    override fun getItemCount(): Int =5

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> Vp1Fragment()
            1 -> Vp2Fragment()
            2 -> Vp3Fragment()
            3 -> Vp4Fragment()
            else -> Vp5Fragment()
        }
    }
}
