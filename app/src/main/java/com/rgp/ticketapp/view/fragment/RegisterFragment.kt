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
import com.rgp.ticketapp.viewmodel.RegisterViewModel
import com.rgp.ticketapp.viewmodel.SharedPreferencesManager

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity: MainActivity = activity!! as MainActivity
        val authenticationViewModel = ViewModelProvider(
            mainActivity,
            SavedStateViewModelFactory(mainActivity.application!!, mainActivity)
        )
            .get(AuthenticationViewModel::class.java)
        val registerViewModel = ViewModelProviders.of(this)[RegisterViewModel::class.java]

        //callback to registerButton
        view.findViewById<Button>(R.id.registerButton).setOnClickListener {
            val email = view.findViewById<EditText>(R.id.emailRegisterEditText).text.toString()
            val password =
                view.findViewById<EditText>(R.id.passwordRegisterEditText).text.toString()
            val name = view.findViewById<EditText>(R.id.nameRegisterEditText).text.toString()
            registerViewModel.register(email = email, password = password, name = name)
                .observe(viewLifecycleOwner, Observer {
                    it?.let {
                        SharedPreferencesManager.saveSessionToPreferences(
                            context = context!!,
                            session = it
                        )
                        authenticationViewModel.saveSession(it)
                        //navigate to Tickets Fragment
                        findNavController().navigate(R.id.action_global_ticketsFragment)
                        //save credentials if checked option
                    }
                })
        }
    }


}
