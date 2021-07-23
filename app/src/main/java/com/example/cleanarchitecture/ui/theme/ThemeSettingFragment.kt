package com.example.cleanarchitecture.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SwitchCompat
import androidx.appcompat.widget.Toolbar
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.RecyclerView
import com.bt.presentation.base.uikit.ThemeHelper
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.model.ThemeItem

class ThemeSettingFragment : Fragment() {

    private var themeRecycler: RecyclerView? = null
    private var switchMode: SwitchCompat? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_theme_setting, container, false)

        view.findViewById<Toolbar>(R.id.toolbar).setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        themeRecycler = view.findViewById(R.id.recyclerTheme)
        switchMode = view.findViewById(R.id.switchMode)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val defaultTheme = getThemes().find {
            it.name == sharedPreferences.getString(ThemeHelper.THEME_PREF, ThemeHelper.COLOR_1)
        } ?: getThemes().first()

        themeRecycler?.adapter = ThemeAdapter(getThemes(), defaultTheme) {
            sharedPreferences.edit {
                this.putString(ThemeHelper.THEME_PREF, it.name)
            }
            activity?.recreate()
        }

        val defaultThemeMode =
            sharedPreferences.getString(ThemeHelper.THEME_MODE_PREF, ThemeHelper.DEFAULT_MODE)

        switchMode?.isChecked = defaultThemeMode == ThemeHelper.DARK_MODE
        switchMode?.setOnCheckedChangeListener { _, checked ->
            if (checked) {
                sharedPreferences.edit {
                    this.putString(ThemeHelper.THEME_MODE_PREF, ThemeHelper.DARK_MODE)
                }
            } else {
                sharedPreferences.edit {
                    this.putString(ThemeHelper.THEME_MODE_PREF, ThemeHelper.LIGHT_MODE)
                }
            }
            activity?.recreate()
        }

        return view
    }

    private fun getThemes() = listOf(
        ThemeItem(
            color = "#0094da",
            name = "color1"
        ),
        ThemeItem(
            color = "#4b6580",
            name = "color2"
        ),
        ThemeItem(
            color = "#27ae61",
            name = "color3"
        ),
        ThemeItem(
            color = "#9b58b5",
            name = "color4"
        ),
        ThemeItem(
            color = "#d64457",
            name = "color5"
        ),
        ThemeItem(
            color = "#6e9992",
            name = "color6"
        ),
        ThemeItem(
            color = "#16a086",
            name = "color7"
        ),
        ThemeItem(
            color = "#e67e22",
            name = "color8"
        ),
        ThemeItem(
            color = "#d73c8a",
            name = "color9"
        )
    )
}
