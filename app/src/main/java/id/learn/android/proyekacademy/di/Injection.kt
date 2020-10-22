package id.learn.android.proyekacademy.di

import android.content.Context
import id.learn.android.proyekacademy.data.source.AcademyRepository
import id.learn.android.proyekacademy.data.source.remote.RemoteDataSource
import id.learn.android.proyekacademy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return AcademyRepository.getInstance(remoteDataSource)
    }
}