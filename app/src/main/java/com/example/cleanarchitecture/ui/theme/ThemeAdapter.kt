package com.example.cleanarchitecture.ui.theme

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitecture.R
import com.example.cleanarchitecture.model.ThemeItem

class ThemeAdapter(
    private val themes: List<ThemeItem>,
    defaultTheme: ThemeItem,
    private val listener: ((ThemeItem) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var posSelected = 0
    private var currentTheme = defaultTheme

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_theme, parent, false)

        return ThemeViewHolder(view)
    }

    override fun getItemCount(): Int = themes.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ThemeViewHolder) {
            holder.bindView(themes[position])
        }
    }

    inner class ThemeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val viewTheme = itemView.findViewById<View>(R.id.viewTheme)
        private val iconCheck = itemView.findViewById<ImageView>(R.id.iconCheck)

        init {

            itemView.setOnClickListener {
                if (currentTheme.name == themes[adapterPosition].name) return@setOnClickListener

                currentTheme = themes[adapterPosition]
                listener?.invoke(currentTheme)

                val tmp = posSelected
                posSelected = adapterPosition

                notifyItemChanged(tmp)
                notifyItemChanged(posSelected)
            }
        }

        fun bindView(theme: ThemeItem) {
            viewTheme.setBackgroundColor(Color.parseColor(theme.color))

            if (currentTheme.name == theme.name) {
                iconCheck.visibility = View.VISIBLE
            } else {
                iconCheck.visibility = View.GONE
            }
        }
    }
}
