package com.serhatd.movieapp.ui.login

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.serhatd.movieapp.MainActivity
import com.serhatd.movieapp.R
import com.serhatd.movieapp.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: LoginViewModel by viewModels()
        viewModel = tempViewModel
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        binding.loginViewModel = viewModel
        (requireActivity() as MainActivity).binding.toolbar.title = getString(R.string.title_login)

        createMenu()
        initObservers()
        return binding.root
    }

    private fun initObservers() {
        viewModel.navObserver.observe(viewLifecycleOwner) {
            it?.let {
                // login to user fragment
                // Navigation.findNavController(binding.textInputLayout).navigate()
                Toast.makeText(requireContext(), "Login ok", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun createMenu() {
        requireActivity().addMenuProvider(object: MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // menu.clear()
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }
}