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
import uz.univer.qurbonlikofficial.databinding.ItemSheepByHeadBinding
import java.text.NumberFormat
import java.util.Locale


class SheepsByHeadAdapter :
    ListAdapter<SheepByHeadDataEntity, SheepsByHeadAdapter.EmployeeHolder>(Diff) {
    private var clickDelete: ((SheepByHeadDataEntity) -> Unit)? = null
    private var clickListener: ((SheepByHeadDataEntity) -> Unit)? = null
    private var clickOpenListener: ((SheepByHeadDataEntity) -> Unit)? = null
    private var longClickListener: ((SheepByHeadDataEntity, Int, View) -> Unit)? = null

    inner class EmployeeHolder(private val binding: ItemSheepByHeadBinding) :
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

        fun bind(data: SheepByHeadDataEntity) = binding.apply {
            binding.name.text = data.name.ifEmpty { "" }
            binding.phone.text = data.phoneNumber
            binding.indicator.setImageResource(if (data.debt == 0.0f) R.drawable.bg_paid else R.drawable.bg_debt)
            binding.number.text = "Қўй рақами:${data.sheepNumber.ifEmpty { "" }}"
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

            binding.debt.setTextColor(
                if (data.debt == 0.0f) Color.parseColor("#158C68") else Color.parseColor(
                    "#D0021B"
                )
            )
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

    fun itemClickOpenListener(f: (SheepByHeadDataEntity) -> Unit) {
        clickOpenListener = f
    }

    fun itemDeleteListener(f: (SheepByHeadDataEntity) -> Unit) {
        clickDelete = f
    }

    fun itemLongClickListener(f: (SheepByHeadDataEntity, Int, View) -> Unit) {
        longClickListener = f
    }
}
