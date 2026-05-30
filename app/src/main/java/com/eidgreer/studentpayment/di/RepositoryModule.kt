package com.eidgreer.studentpayment.di

import com.eidgreer.studentpayment.data.repository.GroupRepositoryImpl
import com.eidgreer.studentpayment.data.repository.StudentRepositoryImpl
import com.eidgreer.studentpayment.data.repository.PaymentRepositoryImpl
import com.eidgreer.studentpayment.domain.repository.GroupRepository
import com.eidgreer.studentpayment.domain.repository.StudentRepository
import com.eidgreer.studentpayment.domain.repository.PaymentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindGroupRepository(
        impl: GroupRepositoryImpl
    ): GroupRepository

    @Singleton
    @Binds
    abstract fun bindStudentRepository(
        impl: StudentRepositoryImpl
    ): StudentRepository

    @Singleton
    @Binds
    abstract fun bindPaymentRepository(
        impl: PaymentRepositoryImpl
    ): PaymentRepository
}
