package uz.univer.qurbonlikofficial.data.domain.head

import kotlinx.coroutines.flow.Flow
import uz.univer.qurbonlikofficial.data.entity.SheepByHeadDataEntity

interface SheepByHeadRepository {

    fun getAllSheeps(): Flow<List<SheepByHeadDataEntity>>
    suspend fun getSheep(id: Long): SheepByHeadDataEntity
    suspend fun search(query: String): Flow<List<SheepByHeadDataEntity>>
    suspend fun updateSheep(sheepDataEntity: SheepByHeadDataEntity)
    suspend fun deleteSheep(sheepDataEntity: SheepByHeadDataEntity)
    suspend fun addSheep(sheepDataEntity: SheepByHeadDataEntity)
    suspend fun checkSheepKg(sheepDataEntity: SheepByHeadDataEntity): SheepByHeadDataEntity?

}