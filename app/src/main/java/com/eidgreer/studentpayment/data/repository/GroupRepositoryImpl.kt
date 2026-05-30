package com.eidgreer.studentpayment.data.repository

import com.eidgreer.studentpayment.data.local.dao.GroupDao
import com.eidgreer.studentpayment.data.local.entity.GroupEntity
import com.eidgreer.studentpayment.domain.repository.GroupRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupDao: GroupDao
) : GroupRepository {
    override suspend fun addGroup(group: GroupEntity): Long {
        return groupDao.insert(group)
    }

    override suspend fun updateGroup(group: GroupEntity) {
        groupDao.update(group)
    }

    override suspend fun deleteGroup(group: GroupEntity) {
        groupDao.delete(group)
    }

    override fun getAllGroups(): Flow<List<GroupEntity>> {
        return groupDao.getAllGroups()
    }

    override fun getGroupById(groupId: Int): Flow<GroupEntity> {
        return groupDao.getGroupById(groupId)
    }

    override fun searchGroups(query: String): Flow<List<GroupEntity>> {
        return groupDao.searchGroups(query)
    }
}
