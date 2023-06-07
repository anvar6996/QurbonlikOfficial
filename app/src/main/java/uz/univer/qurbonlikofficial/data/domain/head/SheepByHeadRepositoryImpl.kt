package uz.univer.qurbonlikofficial.data.domain.head

import kotlinx.coroutines.flow.Flow
import uz.univer.qurbonlikofficial.data.dao.SheepByHeadDao
import uz.univer.qurbonlikofficial.data.entity.SheepByHeadDataEntity
import javax.inject.Inject


class SheepByHeadRepositoryImpl @Inject constructor(private val dao: SheepByHeadDao) :
    SheepByHeadRepository {

    override fun getAllSheeps(): Flow<List<SheepByHeadDataEntity>> {
        return dao.getSheeps()
    }

    override suspend fun getSheep(id: Long): SheepByHeadDataEntity {
        return dao.getSheepById(id)
    }

    override suspend fun search(query: String): Flow<List<SheepByHeadDataEntity>> {
        return dao.searchUsers("%${query}%")
    }

    override suspend fun updateSheep(sheepDataEntity: SheepByHeadDataEntity) {
        dao.update(sheepDataEntity)
    }

    override suspend fun deleteSheep(sheepDataEntity: SheepByHeadDataEntity) {
        dao.deleteSheep(sheepDataEntity)
    }

    override suspend fun addSheep(sheepDataEntity: SheepByHeadDataEntity) {
        dao.insertSheep(sheepDataEntity)
    }

    override suspend fun checkSheepKg(sheepDataEntity: SheepByHeadDataEntity): SheepByHeadDataEntity? {
        return dao.checkSheepKg(sheepDataEntity.sheepNumber)
    }

}