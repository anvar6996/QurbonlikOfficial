package uz.univer.qurbonlikofficial.ui.head

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.univer.qurbonlikofficial.R
import uz.univer.qurbonlikofficial.databinding.FragmentAddSheepByHeadBinding
import uz.univer.qurbonlikofficial.databinding.FragmentSheepsByHeadBinding

class SheepsByHeadFragment : Fragment(R.layout.fragment_sheeps_by_head) {
    private val binding by viewBinding(FragmentSheepsByHeadBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}