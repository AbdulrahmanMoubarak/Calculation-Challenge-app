package com.training.calculationchallengev2.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_settings.view.*
import java.util.*

class Lang {
    companion object{
         fun checkLang(fragment: Fragment): String{
            var sp = fragment.requireActivity().getSharedPreferences("langloc", Context.MODE_PRIVATE)
            return  sp.getString("lang", "en").toString()
        }

         fun saveLang(fragment: Fragment,lang: String){
            var sp = fragment.requireActivity().getSharedPreferences("langloc", Context.MODE_PRIVATE)
            var editor = sp.edit()
             editor.putString("lang", lang).apply()
        }

         fun setLocale(fragment: Fragment, activity: Activity, lang: String?) {
            val config = fragment.resources.configuration

            val locale = Locale(lang)
            Locale.setDefault(locale)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                config.setLocale(locale)
            else
                config.locale = locale

             if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N)
                 activity.createConfigurationContext(config)


            fragment.resources.updateConfiguration(config, fragment.resources.displayMetrics)
        }
    }
}