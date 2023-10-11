package com.levid.levid_p1_ap2.di

import android.content.Context
import androidx.room.Room
import com.levid.levid_p1_ap2.data.AppDbContext
import com.levid.levid_p1_ap2.data.repository.CounterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn ( SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDbContext(@ApplicationContext appDbContext: Context): AppDbContext =
        Room.databaseBuilder(
            appDbContext,
            AppDbContext::class.java,
            "AppDb.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideDivisionDao(appDb: AppDbContext) = appDb.divisionDao()
    @Singleton
    @Provides
    fun providePreferences(@ApplicationContext context: Context) = CounterRepository(context)
}