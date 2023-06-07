package uz.univer.qurbonlikofficial.ui.head

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.univer.qurbonlikofficial.R
import uz.univer.qurbonlikofficial.data.entity.SheepByHeadDataEntity
import uz.univer.qurbonlikofficial.data.model.StaticValues
import uz.univer.qurbonlikofficial.databinding.FragmentEditSheepByHeadBinding
import uz.univer.qurbonlikofficial.ui.vm.MainViewModel
import uz.univer.qurbonlikofficial.utils.date.showToast
import java.text.NumberFormat
import java.util.Locale

@AndroidEntryPoint
class EditSheepsByHeadFragment : Fragment(R.layout.fragment_edit_sheep_by_head) {
    private val binding by viewBinding(FragmentEditSheepByHeadBinding::bind)
    private val viewModel by viewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
            StaticValues.sheepByHeadDataEntity.let { data ->
                name.setText(data.name)
                surname.setText(data.surname)
                sheepNumber.setText(data.sheepNumber)
                phone.setText(data.phoneNumber.substring(2, data.phoneNumber.length))

                val formattedCost = numberFormat.format(data.price)
                val formattedAmmount = numberFormat.format(data.paidAmmount)
                val formattedDebt = numberFormat.format(data.debt)
                sheepCost.setText(formattedCost.toString().trim().replace("\\p{Zs}+".toRegex(), ""))
                paidAmmount.setText(
                    formattedAmmount.toString().trim().replace("\\p{Zs}+".toRegex(), "")
                )
                debt.setText(formattedDebt.toString().trim().replace("\\p{Zs}+".toRegex(), ""))
            }
        }
        binding.btnByKg.setOnClickListener { saveSheep() }
        binding.paidAmmount.addTextChangedListener {
            if (it.toString().isNotEmpty() && binding.sheepCost.text.toString()
                    .isNotEmpty() && it.toString().toFloat() <= binding.sheepCost.text.toString()
                    .toFloat()
            ) {
                binding.debt.setText(
                    (binding.sheepCost.text.toString().toFloat() - it.toString()
                        .toFloat()).toInt().toString()
                )
            }
        }
        binding.sheepCost.addTextChangedListener {
            if (it.toString().isNotEmpty() && binding.paidAmmount.text.toString()
                    .isNotEmpty() && it.toString().toFloat() >= binding.paidAmmount.text.toString()
                    .toFloat()
            ) {
                binding.debt.setText(
                    (it.toString().toFloat() - binding.paidAmmount.text.toString()
                        .toFloat()).toInt().toString()
                )
            }
        }
        initObserver()
    }

    private fun initObserver() {
        viewModel.successAdd.onEach {
            showToast(it)
            findNavController().popBackStack()
        }.launchIn(lifecycleScope)

        viewModel.errorAdd.onEach {
            showToast(it)
            binding.sheepNumber.setError("Бундай ракамли кўй мавжуд")
        }.launchIn(lifecycleScope)
    }

    private fun saveSheep() {
        binding.apply {
            if (name.text.toString().isEmpty()) {
                name.setError("Харидор исмини киритинг!")
                return
            }
            if (surname.text.toString().isEmpty()) {
                surname.setError("Харидор фамилиясини киритинг!")
                return
            }
            if (phone.unMaskedText.toString().length == 7) {
                phone.setError("Харидор телефон рақамини киритинг!")
                return
            }

            if (sheepCost.text.toString().isEmpty()) {
                sheepCost.setError("Қўй нархини киритинг!")
                return
            }
            if (paidAmmount.text.toString().isEmpty()) {
                paidAmmount.setError("Тўланган суммани киритинг!")
                return
            }
            if (debt.text.toString().isEmpty()) {
                return
            }
            viewModel.updateByHeadSheep(
                SheepByHeadDataEntity(
                    id = StaticValues.sheepByHeadDataEntity.id,
                    name = name.text.toString(),
                    sheepNumber = sheepNumber.text.toString(),
                    surname = surname.text.toString(),
                    phoneNumber = "998${phone.unMaskedText.toString()}",
                    price = sheepCost.text.toString().toFloat(),
                    paidAmmount = paidAmmount.text.toString().toFloat(),
                    debt = debt.text.toString().toFloat()
                )
            )
        }
    }
}