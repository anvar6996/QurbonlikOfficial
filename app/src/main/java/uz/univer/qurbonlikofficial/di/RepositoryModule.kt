package uz.univer.qurbonlikofficial.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.univer.qurbonlikofficial.data.domain.head.SheepByHeadRepository
import uz.univer.qurbonlikofficial.data.domain.head.SheepByHeadRepositoryImpl
import uz.univer.qurbonlikofficial.data.domain.kg.SheepByKgRepository
import uz.univer.qurbonlikofficial.data.domain.kg.SheepByKgRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindSheepByKgRepository(impl: SheepByKgRepositoryImpl): SheepByKgRepository

    @Binds
    fun bindSheepByHeadRepository(impl: SheepByHeadRepositoryImpl): SheepByHeadRepository

}