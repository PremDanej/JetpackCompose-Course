package com.merp.jet.note.app.di

import android.content.Context
import androidx.room.Room
import com.merp.jet.note.app.data.NoteDatabase
import com.merp.jet.note.app.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDatabaseDao = noteDatabase.noteDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(context, NoteDatabase::class.java, "note_dp")
            .fallbackToDestructiveMigration()
            .build()
}