package com.eidgreer.studentpayment.data.repository

import com.eidgreer.studentpayment.data.local.dao.PaymentDao
import com.eidgreer.studentpayment.data.local.entity.PaymentEntity
import com.eidgreer.studentpayment.domain.repository.PaymentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val paymentDao: PaymentDao
) : PaymentRepository {
    override suspend fun addPayment(payment: PaymentEntity): Long {
        return paymentDao.insert(payment)
    }

    override suspend fun updatePayment(payment: PaymentEntity) {
        paymentDao.update(payment)
    }

    override suspend fun deletePayment(payment: PaymentEntity) {
        paymentDao.delete(payment)
    }

    override fun getPaymentsByStudent(studentId: Int): Flow<List<PaymentEntity>> {
        return paymentDao.getPaymentsByStudent(studentId)
    }

    override fun getPaymentByMonth(studentId: Int, month: Int, year: Int): Flow<PaymentEntity?> {
        return paymentDao.getPaymentByMonth(studentId, month, year)
    }

    override fun getTotalPaymentForMonth(month: Int, year: Int): Flow<Double?> {
        return paymentDao.getTotalPaymentForMonth(month, year)
    }

    override fun getUnpaidPayments(): Flow<List<PaymentEntity>> {
        return paymentDao.getUnpaidPayments()
    }

    override fun getUnpaidCountByStudent(studentId: Int): Flow<Int> {
        return paymentDao.getUnpaidCountByStudent(studentId)
    }
}
