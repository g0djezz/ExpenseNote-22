package com.example.expensenote_22

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.expensenote_22.databinding.ItemExpenseBinding
import java.util.Locale

class ExpenseAdapter(
    private val items: MutableList<ExpenseItem> = mutableListOf(),
    private val onItemDeleted: (ExpenseItem) -> Unit = {},
) : RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder>() {

    class ExpenseViewHolder(val binding: ItemExpenseBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ItemExpenseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return ExpenseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            tvCategoryBadge.text = item.category
            tvItemTitle.text = item.title
            tvItemTime.text = item.date
            tvItemAmount.text = String.format(Locale.getDefault(), "- %.2f ฿", item.amount)

            root.setOnLongClickListener {
                onItemDeleted(item)
                true
            }
        }
    }

    override fun getItemCount(): Int = items.size

    fun addExpense(item: ExpenseItem) {
        items.add(0, item)
        notifyItemInserted(0)
    }

    fun removeExpense(item: ExpenseItem) {
        val index = items.indexOf(item)
        if (index != -1) {
            items.removeAt(index)
            notifyItemRemoved(index)
        }
    }

    fun getTotalAmount(): Double {
        return items.sumOf { it.amount }
    }
}