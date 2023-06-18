package com.example.entregasuperpoderesandroid.utils

import com.example.entregasuperpoderesandroid.data.Repository
import com.example.entregasuperpoderesandroid.data.RepositoryImpl
import com.example.entregasuperpoderesandroid.data.local.ILocalDataSource
import com.example.entregasuperpoderesandroid.data.local.LocalDataSourceImpl
import com.example.entregasuperpoderesandroid.data.remote.RemoteDataSource
import com.example.entregasuperpoderesandroid.data.remote.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoriesModule {

    @Binds
    abstract fun bindsRepository(repositoryImpl: RepositoryImpl): Repository

    @Binds
    abstract fun bindsLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): ILocalDataSource

    @Binds
    abstract fun bindsRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}