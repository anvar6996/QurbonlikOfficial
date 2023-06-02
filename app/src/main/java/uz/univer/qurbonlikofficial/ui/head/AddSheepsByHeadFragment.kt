package uz.univer.qurbonlikofficial.ui.head

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.univer.qurbonlikofficial.R
import uz.univer.qurbonlikofficial.databinding.FragmentAddSheepByHeadBinding
@AndroidEntryPoint
class AddSheepsByHeadFragment : Fragment(R.layout.fragment_add_sheep_by_head) {
    private val binding by viewBinding(FragmentAddSheepByHeadBinding::bind)
}