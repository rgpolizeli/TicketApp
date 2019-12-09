package com.rgp.ticketapp.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.rgp.ticketapp.R
import com.rgp.ticketapp.model.Session
import com.rgp.ticketapp.view.MainActivity
import com.rgp.ticketapp.viewmodel.AuthenticationViewModel
import com.rgp.ticketapp.viewmodel.SharedPreferencesManager

class SplashFragment : Fragment() {

    val authenticationViewModel: AuthenticationViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainActivity: MainActivity = activity!! as MainActivity
        val authenticationViewModel = ViewModelProvider(
            mainActivity,
            SavedStateViewModelFactory(mainActivity.application!!, mainActivity)
        )
            .get(AuthenticationViewModel::class.java)

        if (authenticationViewModel.getSession() == null) {
            val session: Session? = SharedPreferencesManager.getSessionFromPreferences(context!!)
            if (session == null) {
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            } else {
                authenticationViewModel.saveSession(session)
                //navigate to Tickets Fragment
                findNavController().navigate(R.id.action_global_ticketsFragment)
            }
        } else {
            //navigate to Tickets Fragment
            findNavController().navigate(R.id.action_global_ticketsFragment)
        }
    }


}
