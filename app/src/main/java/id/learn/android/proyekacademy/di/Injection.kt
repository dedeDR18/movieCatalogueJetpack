package id.learn.android.proyekacademy.di

import android.content.Context
import id.learn.android.proyekacademy.data.source.AcademyRepository
import id.learn.android.proyekacademy.data.source.local.LocalDataSource
import id.learn.android.proyekacademy.data.source.local.room.AcademyDatabase
import id.learn.android.proyekacademy.data.source.remote.RemoteDataSource
import id.learn.android.proyekacademy.utils.AppExecutors
import id.learn.android.proyekacademy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val database = AcademyDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.academyDao())
        val appExecutors = AppExecutors()

        return AcademyRepository.getInstance(remoteDataSource, localDataSource, appExecutors
        )
    }
}