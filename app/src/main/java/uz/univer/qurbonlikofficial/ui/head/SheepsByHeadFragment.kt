package uz.univer.qurbonlikofficial.ui.head

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.app.ShareCompat
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
import uz.univer.qurbonlikofficial.databinding.FragmentSheepsByHeadBinding
import uz.univer.qurbonlikofficial.ui.adapter.SheepsByHeadAdapter
import uz.univer.qurbonlikofficial.ui.vm.MainViewModel
import uz.univer.qurbonlikofficial.utils.date.getCurrentDate
import uz.univer.qurbonlikofficial.utils.date.getCurrentTime
import uz.univer.qurbonlikofficial.utils.exel.Constants
import uz.univer.qurbonlikofficial.utils.exel.ExcelHeadUtils
import uz.univer.qurbonlikofficial.utils.exel.FileShareUtils

@AndroidEntryPoint
class SheepsByHeadFragment : Fragment(R.layout.fragment_sheeps_by_head) {
    private val binding by viewBinding(FragmentSheepsByHeadBinding::bind)
    private val viewModel by viewModels<MainViewModel>()
    private val sheepsByHeadAdapter = SheepsByHeadAdapter()
    private var listSheepsByHead = ArrayList<SheepByHeadDataEntity>()
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (checkWriteFilePermissionGranted()) {
            if (listSheepsByHead.isNotEmpty()) {
                ExcelHeadUtils.exportDataIntoWorkbook(
                    requireContext(),
                    getCurrentDate() + getCurrentTime() + Constants.EXCEL_FILE_NAME_HEAD,
                    listSheepsByHead
                )
                val fileUri: Uri = initiateSharing()
                launchShareFileIntent(fileUri)
            }else{
                Toast.makeText(
                    requireContext(), "Малумотлар йўқ", Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(
                requireContext(), "Хотирадан фойдаланишга рухсат берилмади", Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun checkWriteFilePermissionGranted() = (ActivityCompat.checkSelfPermission(
        requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllSheepByHead()
        binding.upload.setOnClickListener {
            if (checkWriteFilePermissionGranted()) {
                if (listSheepsByHead.isNotEmpty()) {
                    ExcelHeadUtils.exportDataIntoWorkbook(
                        requireContext(),
                        getCurrentDate() + getCurrentTime() + Constants.EXCEL_FILE_NAME_HEAD,
                        listSheepsByHead
                    )
                    val fileUri: Uri = initiateSharing()
                    launchShareFileIntent(fileUri)
                }
            } else {
                locationPermissionRequest.launch(
                    arrayOf(
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                )
            }
        }
        sheepsByHeadAdapter.itemClickListener {
            val phone = "+${it.phoneNumber}"
            val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
            startActivity(intent)
        }

        binding.recyclerSheep.adapter = sheepsByHeadAdapter
        viewModel.flowSheepsByHead.onEach {
            sheepsByHeadAdapter.submitList(it)
            listSheepsByHead = it as ArrayList<SheepByHeadDataEntity>
        }.launchIn(lifecycleScope)

        binding.searchBadge.addTextChangedListener {
            viewModel.searchSheepsByHead(it.toString())
        }
        binding.addSheep.setOnClickListener {
            findNavController().navigate(SheepsByHeadFragmentDirections.actionSheepsByHeadFragmentToAddSheepsByHeadFragment())
        }
        sheepsByHeadAdapter.itemDeleteListener {
            viewModel.deleteByHeadSheep(it)
        }
    }

    fun initiateSharing(): Uri {
        return FileShareUtils.accessFile(
            requireContext(), getCurrentDate() + getCurrentTime() + Constants.EXCEL_FILE_NAME_HEAD
        )
    }

    private fun launchShareFileIntent(uri: Uri) {
        val intent = ShareCompat.IntentBuilder.from(requireActivity()).setType("application/pdf")
            .setStream(uri)
            .setChooserTitle("Select application to share file").createChooserIntent()
            .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        startActivity(intent)
    }
}