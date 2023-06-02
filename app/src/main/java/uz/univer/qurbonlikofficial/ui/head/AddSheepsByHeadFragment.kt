package uz.univer.qurbonlikofficial.ui.head

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.univer.qurbonlikofficial.R
import uz.univer.qurbonlikofficial.data.entity.SheepByHeadDataEntity
import uz.univer.qurbonlikofficial.databinding.FragmentAddSheepByHeadBinding
import uz.univer.qurbonlikofficial.ui.vm.MainViewModel

@AndroidEntryPoint
class AddSheepsByHeadFragment : Fragment(R.layout.fragment_add_sheep_by_head) {
    private val binding by viewBinding(FragmentAddSheepByHeadBinding::bind)
    private val viewModel by viewModels<MainViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnByKg.setOnClickListener {
                saveSheep()
                findNavController().popBackStack()
            }
        }
    }

    private fun saveSheep() {
        binding.apply {
            if (name.text.toString().isEmpty()) {
                name.setError("Xaridor ismini kiriting!")
                return
            }
            if (surname.text.toString().isEmpty()) {
                name.setError("Xaridor familiyasini kiriting!")
                return
            }
            if (phone.unMaskedText.toString().length == 7) {
                name.setError("Xaridor telefon raqamini kiriting!")
                return
            }
            if (sheepNumber.text.toString().isEmpty()) {
                name.setError("Qo'y raqamini kiriting!")
                return
            }
            if (sheepCost.text.toString().isEmpty()) {
                name.setError("Qo'y narxini kiriting!")
                return
            }
            if (debt.text.toString().isEmpty()) {
                return
            }

            viewModel.addSheepByHead(
                SheepByHeadDataEntity(
                    id = 0,
                    name = name.text.toString(),
                    surname = surname.text.toString(),
                    phoneNumber = "998${phone.unMaskedText.toString()}",
                    price = sheepCost.text.toString(),
                    debt = debt.text.toString().toFloat()
                )
            )
        }
    }
}