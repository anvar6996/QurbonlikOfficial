package uz.univer.qurbonlikofficial

import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.univer.qurbonlikofficial.databinding.FragmentMenuBinding

@AndroidEntryPoint
class MenuFragment :Fragment(R.layout.fragment_menu){
    private val bindingProperty by viewBinding(FragmentMenuBinding::bind)

}