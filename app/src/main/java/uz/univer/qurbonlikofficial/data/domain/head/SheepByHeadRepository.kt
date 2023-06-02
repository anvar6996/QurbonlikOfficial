package uz.univer.qurbonlikofficial.data.domain.head

import kotlinx.coroutines.flow.Flow
import uz.univer.qurbonlikofficial.data.entity.SheepByHeadDataEntity
import uz.univer.qurbonlikofficial.data.entity.SheepByKgDataEntity

interface SheepByHeadRepository {

  fun getAllSheeps(): Flow<List<SheepByHeadDataEntity>>
  suspend fun getSheep(id:Long): SheepByHeadDataEntity
  suspend fun updateSheep(sheepDataEntity: SheepByHeadDataEntity)
  suspend fun deleteSheep(sheepDataEntity: SheepByHeadDataEntity)
  suspend fun addSheep(sheepDataEntity: SheepByHeadDataEntity)

}