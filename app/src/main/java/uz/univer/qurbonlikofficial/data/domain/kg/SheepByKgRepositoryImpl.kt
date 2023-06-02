package uz.univer.qurbonlikofficial.data.domain.kg

import kotlinx.coroutines.flow.Flow
import uz.univer.qurbonlikofficial.data.dao.SheepByHeadDao
import uz.univer.qurbonlikofficial.data.dao.SheepByKgDao
import uz.univer.qurbonlikofficial.data.entity.SheepByKgDataEntity
import javax.inject.Inject


class SheepByKgRepositoryImpl @Inject constructor(private val dao: SheepByKgDao) :
    SheepByKgRepository {
  override fun getAllSheeps(): Flow<List<SheepByKgDataEntity>> {
        return dao.getSheeps()
    }

    override suspend fun getSheep(id: Long): SheepByKgDataEntity {
        return dao.getSheepById(id)
    }

    override suspend fun search(query: String): Flow<List<SheepByKgDataEntity>> {
        return dao.searchUsers("%${query}%")
    }

    override suspend fun updateSheep(sheepDataEntity: SheepByKgDataEntity) {
        dao.update(sheepDataEntity)
    }

    override suspend fun deleteSheep(sheepDataEntity: SheepByKgDataEntity) {
        dao.deleteSheep(sheepDataEntity)
    }

    override suspend fun addSheep(sheepDataEntity: SheepByKgDataEntity) {
        dao.insertSheep(sheepDataEntity)
    }

}