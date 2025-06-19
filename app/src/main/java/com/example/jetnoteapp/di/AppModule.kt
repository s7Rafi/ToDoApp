package com.example.jetnoteapp.di

import android.content.Context
import androidx.room.Room
import com.example.jetnoteapp.data.NoteDatabase
import com.example.jetnoteapp.data.NoteDatabaseDao
import com.example.jetnoteapp.model.Note
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase : NoteDatabase) : NoteDatabaseDao{
        return noteDatabase.noteDao()
    }


    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) : NoteDatabase
     = Room.databaseBuilder(context,
        NoteDatabase::class.java,
         "notes_tbl")
         .fallbackToDestructiveMigrationFrom()
         .build()


}