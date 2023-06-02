package uz.univer.qurbonlikofficial.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.univer.qurbonlikofficial.data.base.AppBase
import uz.univer.qurbonlikofficial.data.domain.kg.SheepByKgRepository
import uz.univer.qurbonlikofficial.data.domain.kg.SheepByKgRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

  @Provides
  @Singleton
  fun provideNoteDatabase(@ApplicationContext app: Context): AppBase {
    return Room.databaseBuilder(
      app, AppBase::class.java, AppBase.DATABASE_NAME
    ).build()
  }

  @Provides
  @Singleton
  fun provideEasyToDoRepository(db: AppBase): SheepByKgRepository {
    return SheepByKgRepositoryImpl(db.getSheepDao())
  }
}