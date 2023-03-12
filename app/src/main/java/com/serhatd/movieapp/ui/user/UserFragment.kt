package com.serhatd.movieapp.ui.user

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.serhatd.movieapp.MainActivity
import com.serhatd.movieapp.R
import com.serhatd.movieapp.databinding.FragmentUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: UserViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        (requireActivity() as MainActivity).binding.toolbar.title = getString(R.string.title_user)

        createMenu()
        initObservers()
        return binding.root
    }

    private fun initObservers() {

    }

    private fun createMenu() {
        requireActivity().addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_user, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.actionChangePassword -> changePassword()
                    R.id.actionLogout -> logout()
                    else -> return false
                }

                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun changePassword() {

    }

    private fun logout() {
        viewModel.removeCurrentSession()
        Navigation.findNavController(binding.frgUserRecyclerView).popBackStack()
    }
}