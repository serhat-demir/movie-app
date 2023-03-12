package com.serhatd.movieapp.ui.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.serhatd.movieapp.MainActivity
import com.serhatd.movieapp.R
import com.serhatd.movieapp.databinding.FragmentUserBinding

class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        (requireActivity() as MainActivity).binding.toolbar.title = getString(R.string.title_user)

        return binding.root
    }
}