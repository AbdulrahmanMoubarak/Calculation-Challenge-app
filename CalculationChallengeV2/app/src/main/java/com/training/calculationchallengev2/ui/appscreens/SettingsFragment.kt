package com.training.calculationchallengev2.ui.appscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.training.calculationchallengev2.R
import kotlinx.android.synthetic.main.fragment_settings.view.*
import android.util.Log
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.training.calculationchallengev2.ui.main.DatabaseViewModel
import com.training.calculationchallengev2.util.Lang.Companion.checkLang
import com.training.calculationchallengev2.util.Lang.Companion.saveLang
import com.training.calculationchallengev2.util.Lang.Companion.setLocale
import java.util.concurrent.Executors


class SettingsFragment : Fragment() {
    private val TAG = "SettingsFragment"
    private var selected_lang = "en"
    private lateinit var viewModel:DatabaseViewModel
    private var thread = Executors.newFixedThreadPool(1)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = DatabaseViewModel(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_settings, container, false)

        Log.d(TAG, "onCreateView: ${checkLang(this)}")
        if("en".equals(checkLang(this))){
            this.activity?.let { setLocale(this, it, "en")}
            view.radioEn.isChecked = true
            selected_lang = "en"
        }
        else if("ar".equals(checkLang(this))){
            this.activity?.let { setLocale(this, it, "ar")}
            view.radioAr.isChecked = true
            selected_lang = "ar"
        }

        view.radioEn.setOnClickListener{
            it.radioEn.isChecked = true
            selected_lang = "en"
        }

        view.radioAr.setOnClickListener{
            it.radioAr.isChecked = true
            selected_lang = "ar"
        }


        view.buttonDelete.setOnClickListener {
            thread.execute {
                viewModel.deletetData()
                Snackbar.make(view, getString(R.string.data_deleted), Snackbar.LENGTH_LONG)
                    .show()
            }
        }

        view.buttonApply.setOnClickListener {
            saveLang(this, selected_lang)
            findNavController().navigate(R.id.action_settingsFragment_to_homeFragment)
        }



        return view
    }


}