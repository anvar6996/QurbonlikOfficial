package uz.univer.qurbonlikofficial.ui.kg

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
import uz.univer.qurbonlikofficial.data.entity.SheepByKgDataEntity
import uz.univer.qurbonlikofficial.databinding.FragmentAddSheepByKgBinding
import uz.univer.qurbonlikofficial.ui.vm.MainViewModel
import uz.univer.qurbonlikofficial.utils.date.showToast

@AndroidEntryPoint
class AddSheepsByKgFragment : Fragment(R.layout.fragment_add_sheep_by_kg) {
    private val binding by viewBinding(FragmentAddSheepByKgBinding::bind)
    private val viewModel by viewModels<MainViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnByKg.setOnClickListener {
                saveSheep()
            }
        }
        binding.paidAmmount.addTextChangedListener {
            if (it.toString().isNotEmpty() && binding.sheepCost.text.toString()
                    .isNotEmpty() && binding.sheepWeight.text.toString().isNotEmpty() && it.toString().toFloat() <= binding.sheepCost.text.toString().toFloat() * binding.sheepWeight.text.toString().toFloat()
            ) {
                binding.debt.setText(
                    (binding.sheepCost.text.toString().toFloat() * binding.sheepWeight.text.toString().toFloat() - it.toString()
                        .toFloat()).toInt().toString())
            }
        }
        binding.sheepCost.addTextChangedListener {
            if (it.toString().isNotEmpty() && binding.paidAmmount.text.toString()
                    .isNotEmpty() && it.toString().toFloat()*binding.sheepWeight.text.toString().toFloat() >= binding.paidAmmount.text.toString()
                    .toFloat() && binding.sheepWeight.text.toString().isNotEmpty()
            ) {
                binding.debt.setText(
                    (it.toString().toFloat()*binding.sheepWeight.text.toString().toFloat() - binding.paidAmmount.text.toString()
                        .toFloat()).toInt().toString())
            }
        }
        binding.sheepWeight.addTextChangedListener {
            if (it.toString().isNotEmpty() && binding.paidAmmount.text.toString()
                    .isNotEmpty() && binding.sheepCost.text.toString()
                    .isNotEmpty() && binding.paidAmmount.text.toString()
                    .toFloat() <= binding.sheepCost.text.toString()
                    .toFloat() * it.toString().toFloat()
            ) {
                binding.debt.setText((binding.sheepCost.text.toString()
                    .toFloat() * it.toString().toFloat() - binding.paidAmmount.text.toString()
                    .toFloat()).toInt().toString())
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
            if (sheepNumber.text.toString().isEmpty()) {
                sheepNumber.setError("Қўй рақамини киритинг!")
                return
            }
            if (sheepWeight.text.toString().isEmpty()) {
                sheepWeight.setError("Қўй оғирлигини киритинг!")
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

            viewModel.addSheepByKg(
                SheepByKgDataEntity(
                    0,
                    name = name.text.toString(),
                    surname = surname.text.toString(),
                    phoneNumber = "998${phone.unMaskedText.toString()}",
                    sheepNumber = sheepNumber.text.toString(),
                    weight = sheepWeight.text.toString(),
                    price = sheepCost.text.toString().toFloat(),
                    paidAmount = paidAmmount.text.toString().toFloat(),
                    debt = debt.text.toString().toFloat()
                )
            )
        }
    }
}