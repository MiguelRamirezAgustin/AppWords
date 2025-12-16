package com.example.words.Model

import android.content.Context
import androidx.room.Room
import com.example.words.db.AppDatabase
import com.example.words.db.ChairMovementsDAO
import com.example.words.db.ChairsDao
import com.example.words.db.ChairsTejidoDao
import com.example.words.db.PaintDao
import com.example.words.db.LaborDayDao
import com.example.words.db.MaterialPriceDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @dagger.hilt.android.qualifiers.ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideChairDao(database: AppDatabase): ChairsDao {
        return database.chairsDao()
    }

    @Provides
    fun providePaintDao(database: AppDatabase): PaintDao {
        return database.paintDao()
    }

    @Provides
    fun provideWeeksDao(database: AppDatabase): LaborDayDao {
        return database.laborDayDao()
    }

    @Provides
    fun provideChairTejidoDao(database: AppDatabase): ChairsTejidoDao {
        return database.chairTejido()
    }

    @Provides
    fun provideChairMovementsDao(database: AppDatabase): ChairMovementsDAO {
        return database.chairsMovements()
    }

    @Provides
    fun provideMaterialPriceDao(database: AppDatabase): MaterialPriceDAO {
        return database.materialPrice()
    }


}