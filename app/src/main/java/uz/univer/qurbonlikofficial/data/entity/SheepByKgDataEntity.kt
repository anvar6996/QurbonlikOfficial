package uz.univer.qurbonlikofficial.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SheepByKgDataEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long,
  val name: String,
  val surname: String,
  val sheepNumber: String,
  val phoneNumber: String,
  val weight: String,
  val price: String,
  val paidAmount: String,
  val remainingAmount: String,
)