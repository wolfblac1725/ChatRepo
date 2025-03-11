package com.erik.canseco.chatexamen.di

import com.erik.canseco.chatexamen.data.FireAuthDataSource
import com.erik.canseco.chatexamen.data.FirebaseChatsDataSource
import com.erik.canseco.chatexamen.domain.AuthDataSource
import com.erik.canseco.chatexamen.domain.ChatsDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {


    @Singleton
    @Provides
    fun provideDatabaseReference(): DatabaseReference = Firebase.database.getReference("chats")

    @Singleton
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun provideAuthDataSource(
        fireAuth: FirebaseAuth
    ): AuthDataSource = FireAuthDataSource(
        fireAuth
    )

    @Singleton
    @Provides
    fun provideChatsDataSource(
        databaseReference: DatabaseReference,
    ): ChatsDataSource = FirebaseChatsDataSource(databaseReference)
}