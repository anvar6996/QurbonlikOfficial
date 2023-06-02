package uz.univer.qurbonlikofficial.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.univer.qurbonlikofficial.R
import uz.univer.qurbonlikofficial.data.entity.SheepByKgDataEntity
import uz.univer.qurbonlikofficial.databinding.ItemSheepByKgBinding


class SheepsByKgAdapter : ListAdapter<SheepByKgDataEntity, SheepsByKgAdapter.EmployeeHolder>(Diff) {

    private var clickListener: ((SheepByKgDataEntity) -> Unit)? = null
    private var longClickListener: ((SheepByKgDataEntity, Int, View) -> Unit)? = null

    inner class EmployeeHolder(private val binding: ItemSheepByKgBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { clickListener?.invoke(getItem(bindingAdapterPosition)) }
            binding.root.setOnLongClickListener {
                longClickListener?.invoke(
                    getItem(bindingAdapterPosition), bindingAdapterPosition, binding.root
                )
                return@setOnLongClickListener true
            }
        }

        fun bind(data: SheepByKgDataEntity) = binding.apply {
            binding.name.text = data.name.ifEmpty { "" }
            binding.number.text = data.sheepNumber.ifEmpty { "" }
            binding.phone.text = data.phoneNumber
            binding.indicator.setImageResource(if (data.debt == 0.0f) R.drawable.bg_paid else R.drawable.bg_debt)
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

    fun itemLongClickListener(f: (SheepByKgDataEntity, Int, View) -> Unit) {
        longClickListener = f
    }
}
