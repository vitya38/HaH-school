package com.example.lesson_4_lukin

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lesson_4_lukin.model.BaseInfoItem
import com.example.lesson_4_lukin.model.Decorator
import com.example.lesson_4_lukin.model.DetailInfoItem
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    companion object {
        private const val VIEW_STATE_LOADING = 0
        private const val VIEW_STATE_DATA = 1
        private const val VIEW_STATE_ERROR = 2
    }

    val list = mutableListOf<BaseInfoItem>(
        DetailInfoItem(R.drawable.ic_bill, "Квитанции", "- 40 080,55 ₽", true),
        DetailInfoItem(R.drawable.ic_counter, "Счетчики", "Подайте показания", true),
        DetailInfoItem(R.drawable.ic_installment, "Рассрочка", "Сл. платеж 25.02.2018", false),
        DetailInfoItem(R.drawable.ic_insurance, "Страхование", "Полис до 12.01.2019", false),
        DetailInfoItem(R.drawable.ic_tv, "Интернет и ТВ", "Баланс 350 Р", false),
        DetailInfoItem(R.drawable.ic_homephone, "Домофон", "Подключен", false),
        DetailInfoItem(R.drawable.ic_guard, "Охрана", "Нет", false),
        BaseInfoItem(R.drawable.ic_uk_contacts, "Контакты УК и служб"),
        BaseInfoItem(R.drawable.ic_request, "Мои заявки"),
        BaseInfoItem(R.drawable.ic_about, "Памятка жителя А101")
    )

    private val viewFlipper by lazy { findViewById<ViewFlipper>(R.id.viewFlipper) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewFlipper.displayedChild = VIEW_STATE_DATA
        val gridLayoutManager = GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                if (position % 2 == 0 && list[position + 1] is DetailInfoItem)
                    return 1
                else if (position % 2 == 1 && list[position] is DetailInfoItem)
                    return 1
                else
                    return 2
            }
        }

        val decorator = Decorator(8, 8, 8, 8)
        recyclerView.layoutManager = gridLayoutManager
        val adapter = MainAdapter()
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(decorator)
        adapter.setItems(list)

        adapter.onItemClick = { cardName ->
            Snackbar.make(recyclerView, cardName, Snackbar.LENGTH_SHORT).show()
        }

        val toolbar = findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            val toast = Toast.makeText(applicationContext, R.string.toast_message, Toast.LENGTH_SHORT)
            toast.show()
        }

        toolbar.menu.findItem(R.id.home).setOnMenuItemClickListener {
            val toast = Toast.makeText(applicationContext, R.string.toast_message, Toast.LENGTH_SHORT)
            toast.show()
            true
        }

        toolbar.menu.findItem(R.id.info).setOnMenuItemClickListener {
            val dialogAlert = AlertDialog.Builder(this, R.style.button)
            dialogAlert.setTitle(R.string.alert_title)
            dialogAlert.setMessage(R.string.alert_text)
            dialogAlert.setPositiveButton(
                R.string.positive_button,
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            val alert = dialogAlert.create()
            alert.show()
            true
        }
    }
}
