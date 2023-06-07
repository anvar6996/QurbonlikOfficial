package uz.univer.qurbonlikofficial.ui.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import uz.univer.qurbonlikofficial.data.domain.head.SheepByHeadRepositoryImpl
import uz.univer.qurbonlikofficial.data.domain.kg.SheepByKgRepositoryImpl
import uz.univer.qurbonlikofficial.data.entity.SheepByHeadDataEntity
import uz.univer.qurbonlikofficial.data.entity.SheepByKgDataEntity
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repositoryKg: SheepByKgRepositoryImpl,
    private val repositoryHead: SheepByHeadRepositoryImpl,
) : ViewModel() {

    val flowSheepsByKg = MutableSharedFlow<List<SheepByKgDataEntity>>()
    val flowSheepsByHead = MutableSharedFlow<List<SheepByHeadDataEntity>>()
    val successAdd = MutableSharedFlow<String>()
    val errorAdd = MutableSharedFlow<String>()


    fun deleteByKgSheep(sheepDataEntity: SheepByKgDataEntity) {
        viewModelScope.launch {
            repositoryKg.deleteSheep(sheepDataEntity)
        }
    }

    fun deleteByHeadSheep(sheepDataEntity: SheepByHeadDataEntity) {
        viewModelScope.launch {
            repositoryHead.deleteSheep(sheepDataEntity)
        }
    }

    fun updateByKgSheep(sheepDataEntity: SheepByKgDataEntity) {
        viewModelScope.launch {
            repositoryKg.getAllSheeps().onEach {
                val checkList = it.filter { data ->
                    data.sheepNumber == sheepDataEntity.sheepNumber && data.id != sheepDataEntity.id
                }
                if (checkList.isEmpty()) {
                    repositoryKg.updateSheep(sheepDataEntity)
                    successAdd.emit("Кўй малумотлари узгартирилди")
                } else {
                    errorAdd.emit("Бундай ракамли куй мавжуд")
                }
            }.launchIn(viewModelScope)
        }
    }

    fun updateByHeadSheep(sheepDataEntity: SheepByHeadDataEntity) {
        viewModelScope.launch {
            repositoryHead.getAllSheeps().onEach {
                val checkList = it.filter { data ->
                    data.sheepNumber == sheepDataEntity.sheepNumber && data.id != sheepDataEntity.id
                }
                if (checkList.isEmpty()) {
                    repositoryHead.updateSheep(sheepDataEntity)
                    successAdd.emit("Кўй малумотлари узгартирилди")
                } else {
                    errorAdd.emit("Бундай ракамли кўй мавжуд")
                }
            }.launchIn(viewModelScope)
        }
    }

    fun getAllSheepByKg() {
        viewModelScope.launch {
            repositoryKg.getAllSheeps().onEach {
                flowSheepsByKg.emit(it)
            }.launchIn(viewModelScope)
        }
    }

    fun getAllSheepByHead() {
        viewModelScope.launch {
            repositoryHead.getAllSheeps().onEach {
                flowSheepsByHead.emit(it)
            }.launchIn(viewModelScope)
        }
    }

    fun searchSheepsByHead(query: String) {
        viewModelScope.launch {
            repositoryHead.search(query).onEach {
                flowSheepsByHead.emit(it)
            }.launchIn(viewModelScope)
        }
    }

    fun searchSheepsByKg(query: String) {
        viewModelScope.launch {
            repositoryKg.search(query = query).onEach {
                flowSheepsByKg.emit(it)
            }.launchIn(viewModelScope)
        }
    }

    fun addSheepByKg(sheepDataEntity: SheepByKgDataEntity) {
        viewModelScope.launch {
            repositoryKg.getAllSheeps().onEach {
                val checkList = it.filter { data ->
                    data.sheepNumber == sheepDataEntity.sheepNumber
                }
                if (checkList.isEmpty()) {
                    repositoryKg.addSheep(sheepDataEntity)
                    successAdd.emit("Куй кушилди")
                } else {
                    errorAdd.emit("Бундай ракамли куй мавжуд")
                }
            }.launchIn(viewModelScope)
        }
    }

    fun addSheepByHead(sheepDataEntity: SheepByHeadDataEntity) {
        viewModelScope.launch {
            repositoryHead.getAllSheeps().onEach {
                val checkList = it.filter { data ->
                    data.sheepNumber == sheepDataEntity.sheepNumber
                }
                if (checkList.isEmpty()) {
                    repositoryHead.addSheep(sheepDataEntity)
                    successAdd.emit("Куй кушилди")
                } else {
                    errorAdd.emit("Бундай ракамли куй мавжуд")
                }
            }.launchIn(viewModelScope)
        }
    }

}