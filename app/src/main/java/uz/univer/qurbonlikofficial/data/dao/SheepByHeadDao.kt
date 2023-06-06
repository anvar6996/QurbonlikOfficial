package uz.univer.qurbonlikofficial.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.univer.qurbonlikofficial.data.entity.SheepByHeadDataEntity

@Dao
interface SheepByHeadDao {
    @Query("SELECT * FROM sheepbyheaddataentity  ORDER BY debt DESC")
    fun getSheeps(): Flow<List<SheepByHeadDataEntity>>

    @Query("SELECT * FROM sheepbyheaddataentity WHERE SheepByHeadDataEntity.name LIKE :searchPattern  OR SheepByHeadDataEntity.sheepNumber LIKE :searchPattern  OR SheepByHeadDataEntity.phoneNumber LIKE :searchPattern  OR  SheepByHeadDataEntity.surname LIKE :searchPattern ")
    fun searchUsers(searchPattern: String): Flow<List<SheepByHeadDataEntity>>
    @Query("SELECT * FROM SheepByHeadDataEntity WHERE id = :id")
    suspend fun getSheepById(id: Long): SheepByHeadDataEntity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSheep(sheepDataEntity: SheepByHeadDataEntity)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(sheepDataEntity: SheepByHeadDataEntity)
    @Delete
    suspend fun deleteSheep(sheepDataEntity: SheepByHeadDataEntity)

}