package uz.univer.qurbonlikofficial.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import uz.univer.qurbonlikofficial.data.entity.SheepByKgDataEntity

@Dao
interface SheepByKgDao {
  @Query("SELECT * FROM sheepbykgdataentity")
  fun getSheeps(): Flow<List<SheepByKgDataEntity>>

  @Query("SELECT * FROM SheepByKgDataEntity WHERE id = :id")
  suspend fun getSheepById(id: Long): SheepByKgDataEntity

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertSheep(sheepDataEntity: SheepByKgDataEntity)

  @Update(onConflict = OnConflictStrategy.REPLACE)
  suspend fun update(sheepDataEntity: SheepByKgDataEntity)

  @Delete
  suspend fun deleteSheep(sheepDataEntity: SheepByKgDataEntity)

}