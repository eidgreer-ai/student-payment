package com.eidgreer.studentpayment.domain.repository

import com.eidgreer.studentpayment.data.local.entity.PaymentEntity
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {
    suspend fun addPayment(payment: PaymentEntity): Long
    suspend fun updatePayment(payment: PaymentEntity)
    suspend fun deletePayment(payment: PaymentEntity)
    fun getPaymentsByStudent(studentId: Int): Flow<List<PaymentEntity>>
    fun getPaymentByMonth(studentId: Int, month: Int, year: Int): Flow<PaymentEntity?>
    fun getTotalPaymentForMonth(month: Int, year: Int): Flow<Double?>
    fun getUnpaidPayments(): Flow<List<PaymentEntity>>
    fun getUnpaidCountByStudent(studentId: Int): Flow<Int>
}
