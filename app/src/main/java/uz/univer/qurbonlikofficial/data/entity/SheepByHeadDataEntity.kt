package uz.univer.qurbonlikofficial.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SheepByHeadDataEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val price: String,
    val debt: Float,
)