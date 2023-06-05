package uz.univer.qurbonlikofficial.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.univer.qurbonlikofficial.data.dao.SheepByHeadDao
import uz.univer.qurbonlikofficial.data.dao.SheepByKgDao
import uz.univer.qurbonlikofficial.data.entity.SheepByHeadDataEntity
import uz.univer.qurbonlikofficial.data.entity.SheepByKgDataEntity

@Database(
  entities = [SheepByKgDataEntity::class, SheepByHeadDataEntity::class],
  version = 3
)

abstract class AppBase : RoomDatabase() {
  abstract fun getSheepByKgDao(): SheepByKgDao
  abstract fun getSheepByHeadDao(): SheepByHeadDao

  companion object {
    const val DATABASE_NAME = "Qurbonlik-Base-0.1.3"
  }
}