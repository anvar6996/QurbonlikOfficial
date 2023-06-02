package uz.univer.qurbonlikofficial.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.univer.qurbonlikofficial.data.entity.SheepByHeadDataEntity
import uz.univer.qurbonlikofficial.data.entity.SheepByKgDataEntity

@Dao
interface SheepByHeadDao {
  @Query("SELECT * FROM sheepbyheaddataentity")
  fun getSheeps(): Flow<List<SheepByHeadDataEntity>>

  @Query("SELECT * FROM SheepByHeadDataEntity WHERE id = :id")
  suspend fun getSheepById(id: Long): SheepByHeadDataEntity

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertSheep(sheepDataEntity: SheepByHeadDataEntity)

  @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend fun update(sheepDataEntity: SheepByHeadDataEntity)

  @Delete
  suspend fun deleteSheep(sheepDataEntity: SheepByHeadDataEntity)

}