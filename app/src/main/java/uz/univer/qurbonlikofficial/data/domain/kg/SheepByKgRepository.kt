package uz.univer.qurbonlikofficial.data.domain.kg

import kotlinx.coroutines.flow.Flow
import uz.univer.qurbonlikofficial.data.entity.SheepByKgDataEntity

interface SheepByKgRepository {

  fun getAllSheeps(): Flow<List<SheepByKgDataEntity>>
  suspend fun getSheep(id:Long): SheepByKgDataEntity
  suspend fun updateSheep(sheepDataEntity: SheepByKgDataEntity)
  suspend fun deleteSheep(sheepDataEntity: SheepByKgDataEntity)
  suspend fun addSheep(sheepDataEntity: SheepByKgDataEntity)

}