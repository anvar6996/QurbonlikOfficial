package uz.univer.qurbonlikofficial.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.univer.qurbonlikofficial.R
import uz.univer.qurbonlikofficial.databinding.FragmentMenuBinding

@AndroidEntryPoint
class MenuFragment : Fragment(R.layout.fragment_menu) {
    private val bindingProperty by viewBinding(FragmentMenuBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingProperty.btnByHead.setOnClickListener {

        }

        bindingProperty.btnByKg.setOnClickListener {

        }
    }
}