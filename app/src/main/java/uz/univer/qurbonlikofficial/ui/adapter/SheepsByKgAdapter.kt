package uz.univer.qurbonlikofficial.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.univer.qurbonlikofficial.R
import uz.univer.qurbonlikofficial.data.entity.SheepByHeadDataEntity
import uz.univer.qurbonlikofficial.data.entity.SheepByKgDataEntity
import uz.univer.qurbonlikofficial.databinding.ItemSheepByKgBinding
import java.text.NumberFormat
import java.util.Locale


class SheepsByKgAdapter : ListAdapter<SheepByKgDataEntity, SheepsByKgAdapter.EmployeeHolder>(Diff) {

    private var clickListener: ((SheepByKgDataEntity) -> Unit)? = null
    private var clickDelete: ((SheepByKgDataEntity) -> Unit)? = null
    private var longClickListener: ((SheepByKgDataEntity, Int, View) -> Unit)? = null
    private var clickOpenListener: ((SheepByKgDataEntity) -> Unit)? = null

    inner class EmployeeHolder(private val binding: ItemSheepByKgBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnLongClickListener {
                longClickListener?.invoke(
                    getItem(bindingAdapterPosition), bindingAdapterPosition, binding.root
                )
                return@setOnLongClickListener true
            }
            binding.root.setOnClickListener {
                clickOpenListener?.invoke(
                    getItem(bindingAdapterPosition)
                )
            }
        }

        fun bind(data: SheepByKgDataEntity) = binding.apply {
            binding.name.text = data.name.ifEmpty { "" }
            binding.number.text = "Қўй рақами:${data.sheepNumber.ifEmpty { "" }}"
            binding.phone.text = data.phoneNumber
            binding.indicator.setImageResource(if (data.debt == 0.0f) R.drawable.bg_paid else R.drawable.bg_debt)
            binding.delete.setOnClickListener {
                clickDelete?.invoke(data)
                notifyItemRemoved(absoluteAdapterPosition)
            }
            binding.call.setOnClickListener {
                clickListener?.invoke(data)
            }
            val formattedNumber =
                NumberFormat.getNumberInstance(Locale.getDefault()).format(data.debt)
            binding.debt.text = "Қарз:${formattedNumber} сўм"
            binding.debt.setTextColor(if (data.debt == 0.0f) Color.parseColor("#158C68") else Color.parseColor("#D0021B"))

        }
    }

    private fun getEmptyIfNull(data: String): String = data.ifEmpty { "" }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EmployeeHolder(
        ItemSheepByKgBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sheep_by_kg, parent, false)
        )
    )

    override fun onBindViewHolder(holder: SheepsByKgAdapter.EmployeeHolder, position: Int) {
        holder.bind(getItem(position))
    }


    object Diff : DiffUtil.ItemCallback<SheepByKgDataEntity>() {
        override fun areItemsTheSame(
            oldItem: SheepByKgDataEntity, newItem: SheepByKgDataEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SheepByKgDataEntity, newItem: SheepByKgDataEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    fun itemClickListener(f: (SheepByKgDataEntity) -> Unit) {
        clickListener = f
    }

    fun itemClickOpenListener(f: (SheepByKgDataEntity) -> Unit) {
        clickOpenListener = f
    }
    fun itemDeleteListener(f: (SheepByKgDataEntity) -> Unit) {
        clickDelete = f
    }

    fun itemLongClickListener(f: (SheepByKgDataEntity, Int, View) -> Unit) {
        longClickListener = f
    }
}
