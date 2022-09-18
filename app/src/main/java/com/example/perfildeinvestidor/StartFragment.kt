package com.example.perfildeinvestidor

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.perfildeinvestidor.databinding.FragmentStartBinding
import com.example.perfildeinvestidor.ViewModel
import kotlin.system.exitProcess


class StartFragment : Fragment() {

    private var binding: FragmentStartBinding? = null

    private val sharedViewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this
    }

    fun initSurvey() {

        val name : EditText? = view?.findViewById<EditText>(R.id.editTextPersonName)

        val nameString = name?.text.toString()


        if (nameString.isNotEmpty()) {

            sharedViewModel.setName(nameString)

            sharedViewModel.hasNoQuestionChecked(0, 0)
            Toast.makeText(
                activity,
                "Bem vindo $nameString Escolha uma das respostas a seguir",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_startFragment_to_firstQuestionFragment)

        }else{
            Toast.makeText(
                activity,
                "Por favor digite um nome válido",
                Toast.LENGTH_LONG).show()
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    exitProcess(0)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}