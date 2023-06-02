package uz.univer.qurbonlikofficial.ui.kg

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.univer.qurbonlikofficial.R
import uz.univer.qurbonlikofficial.databinding.FragmentSheepsByKgBinding

@AndroidEntryPoint
class EditSheepsByKgFragment : Fragment(R.layout.fragment_sheeps_by_kg) {
    private val binding by viewBinding(FragmentSheepsByKgBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}