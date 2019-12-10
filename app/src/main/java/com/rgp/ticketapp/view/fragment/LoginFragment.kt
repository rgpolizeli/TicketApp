package com.rgp.ticketapp.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.rgp.ticketapp.R
import com.rgp.ticketapp.view.MainActivity
import com.rgp.ticketapp.viewmodel.AuthenticationViewModel
import com.rgp.ticketapp.viewmodel.LoginViewModel
import com.rgp.ticketapp.viewmodel.SharedPreferencesManager

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //get authentication and login viewmodel.
        val mainActivity: MainActivity = activity!! as MainActivity
        val authenticationViewModel = ViewModelProvider(
            mainActivity,
            SavedStateViewModelFactory(mainActivity.application!!, mainActivity)
        )
            .get(AuthenticationViewModel::class.java)
        val loginViewModel = ViewModelProviders.of(this)[LoginViewModel::class.java]

        //callback to loginButton
        view.findViewById<Button>(R.id.loginButton).setOnClickListener {
            val userName = view.findViewById<EditText>(R.id.emailEditText).text.toString()
            val password = view.findViewById<EditText>(R.id.passwordEditText).text.toString()
            loginViewModel.authenticate(userName, password).observe(viewLifecycleOwner, Observer {

                it?.let {
                    SharedPreferencesManager.saveSessionToPreferences(
                        context = context!!,
                        session = it
                    )
                    authenticationViewModel.saveSession(it)
                    //navigate to Tickets Fragment
                    findNavController().navigate(R.id.action_global_ticketsFragment)

                    //todo: save credentials if checked option
                }
            })
        }

        //callback to registerButton
        view.findViewById<Button>(R.id.registerButton).setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

}
