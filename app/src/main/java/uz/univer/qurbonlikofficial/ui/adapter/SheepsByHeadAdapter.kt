package uz.univer.qurbonlikofficial.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.univer.qurbonlikofficial.R
import uz.univer.qurbonlikofficial.data.entity.SheepByHeadDataEntity
import uz.univer.qurbonlikofficial.databinding.ItemSheepByHeadBinding


class SheepsByHeadAdapter :
    ListAdapter<SheepByHeadDataEntity, SheepsByHeadAdapter.EmployeeHolder>(Diff) {

    private var clickListener: ((SheepByHeadDataEntity) -> Unit)? = null
    private var longClickListener: ((SheepByHeadDataEntity, Int, View) -> Unit)? = null

    inner class EmployeeHolder(private val binding: ItemSheepByHeadBinding) :
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

        fun bind(data: SheepByHeadDataEntity) = binding.apply {
            binding.name.text = data.name.ifEmpty { "" }
            binding.phone.text = data.phoneNumber
            binding.indicator.setImageResource(if (data.debt == 0.0f) R.drawable.bg_paid else R.drawable.bg_debt)
        }
    }

    private fun getEmptyIfNull(data: String): String = data.ifEmpty { "" }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EmployeeHolder(
        ItemSheepByHeadBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_sheep_by_head, parent, false)
        )
    )

    override fun onBindViewHolder(holder: SheepsByHeadAdapter.EmployeeHolder, position: Int) {
        holder.bind(getItem(position))
    }


    object Diff : DiffUtil.ItemCallback<SheepByHeadDataEntity>() {
        override fun areItemsTheSame(
            oldItem: SheepByHeadDataEntity, newItem: SheepByHeadDataEntity
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: SheepByHeadDataEntity, newItem: SheepByHeadDataEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

    }

    fun itemClickListener(f: (SheepByHeadDataEntity) -> Unit) {
        clickListener = f
    }

    fun itemLongClickListener(f: (SheepByHeadDataEntity, Int, View) -> Unit) {
        longClickListener = f
    }
}
