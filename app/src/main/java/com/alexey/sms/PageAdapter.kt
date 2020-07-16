package com.alexey.sms

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class PageAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                SmsFragment()
            }
            1 -> {
                CallFragment()
            }
            else -> SmsFragment()
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                "CMC"
            }
            1 -> {
                "Вызовы"
            }
            else -> {
                "СМС"
            }
        }
    }

}