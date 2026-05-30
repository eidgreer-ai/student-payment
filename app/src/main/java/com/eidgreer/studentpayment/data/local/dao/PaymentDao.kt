package com.eidgreer.studentpayment.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.eidgreer.studentpayment.data.local.entity.PaymentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PaymentDao {
    @Insert
    suspend fun insert(payment: PaymentEntity): Long

    @Update
    suspend fun update(payment: PaymentEntity)

    @Delete
    suspend fun delete(payment: PaymentEntity)

    @Query("SELECT * FROM payments WHERE student_id = :studentId ORDER BY year DESC, month DESC")
    fun getPaymentsByStudent(studentId: Int): Flow<List<PaymentEntity>>

    @Query("SELECT * FROM payments WHERE student_id = :studentId AND month = :month AND year = :year")
    fun getPaymentByMonth(studentId: Int, month: Int, year: Int): Flow<PaymentEntity?>

    @Query("SELECT SUM(amount) FROM payments WHERE month = :month AND year = :year AND is_paid = 1")
    fun getTotalPaymentForMonth(month: Int, year: Int): Flow<Double?>

    @Query("SELECT * FROM payments WHERE is_paid = 0 ORDER BY year ASC, month ASC")
    fun getUnpaidPayments(): Flow<List<PaymentEntity>>

    @Query("SELECT COUNT(*) FROM payments WHERE student_id = :studentId AND is_paid = 0")
    fun getUnpaidCountByStudent(studentId: Int): Flow<Int>
}
