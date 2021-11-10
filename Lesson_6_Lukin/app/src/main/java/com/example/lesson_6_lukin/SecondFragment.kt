package com.example.lesson_6_lukin

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_6_lukin.secondScreen.Adapter
import com.example.lesson_6_lukin.secondScreen.CardItemTypeOne
import com.example.lesson_6_lukin.secondScreen.CardItemTypeTwo
import com.example.lesson_6_lukin.secondScreen.Decorator
import com.example.lesson_6_lukin.R
import com.google.android.material.appbar.MaterialToolbar

class SecondFragment : Fragment(R.layout.fragment_second) {
    val list = mutableListOf<CardItemTypeOne>(
        CardItemTypeOne(R.drawable.ic_water_cold, "Холодная вода", "54656553"),
        CardItemTypeOne(R.drawable.ic_water_hot, "Горячая вода", "54656553"),
        CardItemTypeTwo(R.drawable.ic_electro_copy, "Электроэнергия", "54656553")
    )


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView by lazy { view.findViewById<RecyclerView>(R.id.recyclerView) }
        val gridLayoutManager = GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false)
        val offset = resources.getDimensionPixelOffset(R.dimen.card_margin)
        val decorator = Decorator(offset, offset, offset, resources.getDimensionPixelOffset(R.dimen.card_margin_bottom))
        recyclerView.layoutManager = gridLayoutManager
        val adapter = Adapter()
        recyclerView.adapter = adapter
        adapter.setItems(list)
        recyclerView.addItemDecoration(decorator)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar2)
        toolbar.menu.findItem(R.id.lamp).setOnMenuItemClickListener {
            Toast.makeText(this.context, it.title.toString(), Toast.LENGTH_SHORT).show()
            true
        }
    }
}