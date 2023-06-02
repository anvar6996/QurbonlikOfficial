package uz.univer.qurbonlikofficial.ui.kg

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.univer.qurbonlikofficial.R
import uz.univer.qurbonlikofficial.databinding.FragmentSheepsByKgBinding
import uz.univer.qurbonlikofficial.ui.adapter.SheepsByKgAdapter
import uz.univer.qurbonlikofficial.ui.vm.MainViewModel

@AndroidEntryPoint
class SheepsByKgFragment : Fragment(R.layout.fragment_sheeps_by_kg) {
    private val binding by viewBinding(FragmentSheepsByKgBinding::bind)
    private val viewModel by viewModels<MainViewModel>()
    private val sheepsByKgAdapter = SheepsByKgAdapter()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllSheepByKg()

        binding.recyclerSheep.adapter = sheepsByKgAdapter
        viewModel.flowSheepsByKg.onEach {
            sheepsByKgAdapter.submitList(it)
        }.launchIn(lifecycleScope)

        binding.addSheep.setOnClickListener {
            findNavController().navigate(SheepsByKgFragmentDirections.actionSheepsByKgFragmentToAddSheepsByKgFragment())
        }
    }
}