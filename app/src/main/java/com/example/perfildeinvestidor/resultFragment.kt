package com.example.perfildeinvestidor

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.perfildeinvestidor.databinding.FragmentResultBinding
import kotlin.system.exitProcess


class ResultQuestionFragment : Fragment() {

    private var binding: FragmentResultBinding? = null

    private val sharedViewModel: ViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentResultBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            lifecycleOwner = viewLifecycleOwner

            viewModel = sharedViewModel

            resultQuestionFragment = this@ResultQuestionFragment
        }
        fun returnScoreSum(): String {
            var sum = 0
            var investorType = ""

            for (i in sharedViewModel.returnScore()) {
                sum += i
            }

            if (sum <= 12) investorType = "Conservador"
            if (sum in 13..29) investorType = "Moderado"
            if (sum >= 30) investorType = "Arrojado"
            return investorType
        }


        val assayEndingTxtEditText = view.findViewById<TextView>(R.id.txtQuestion1)

        val assayEndingTxt = "Obrigado ${sharedViewModel.returnName()}! \r\n seu perfil de investidor é ${returnScoreSum()} "

        assayEndingTxtEditText.text = assayEndingTxt



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true)
            {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_resoultFragment2_to_ninthQuestionFragment)
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }


    fun finishAssay() {
        exitProcess(0)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}