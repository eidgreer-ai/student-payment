package com.eidgreer.studentpayment.ui.groups

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eidgreer.studentpayment.data.local.entity.GroupEntity
import com.eidgreer.studentpayment.domain.repository.GroupRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    private val groupRepository: GroupRepository
) : ViewModel() {

    private val _groups = MutableStateFlow<List<GroupEntity>>(emptyList())
    val groups: StateFlow<List<GroupEntity>> = _groups

    private val _uiState = MutableStateFlow<GroupsUiState>(GroupsUiState.Loading)
    val uiState: StateFlow<GroupsUiState> = _uiState

    init {
        loadGroups()
    }

    private fun loadGroups() {
        viewModelScope.launch {
            try {
                groupRepository.getAllGroups().collect { groupList ->
                    _groups.value = groupList
                    _uiState.value = if (groupList.isEmpty()) {
                        GroupsUiState.Empty
                    } else {
                        GroupsUiState.Success
                    }
                }
            } catch (e: Exception) {
                _uiState.value = GroupsUiState.Error(e.message ?: "خطأ غير معروف")
            }
        }
    }

    fun addGroup(name: String, description: String = "") {
        viewModelScope.launch {
            try {
                val group = GroupEntity(name = name, description = description)
                groupRepository.addGroup(group)
            } catch (e: Exception) {
                _uiState.value = GroupsUiState.Error(e.message ?: "فشل إضافة المجموعة")
            }
        }
    }

    fun deleteGroup(group: GroupEntity) {
        viewModelScope.launch {
            try {
                groupRepository.deleteGroup(group)
            } catch (e: Exception) {
                _uiState.value = GroupsUiState.Error(e.message ?: "فشل حذف المجموعة")
            }
        }
    }

    fun updateGroup(group: GroupEntity) {
        viewModelScope.launch {
            try {
                groupRepository.updateGroup(group)
            } catch (e: Exception) {
                _uiState.value = GroupsUiState.Error(e.message ?: "فشل تحديث المجموعة")
            }
        }
    }

    fun searchGroups(query: String) {
        viewModelScope.launch {
            try {
                if (query.isEmpty()) {
                    loadGroups()
                } else {
                    groupRepository.searchGroups(query).collect { groupList ->
                        _groups.value = groupList
                    }
                }
            } catch (e: Exception) {
                _uiState.value = GroupsUiState.Error(e.message ?: "خطأ في البحث")
            }
        }
    }
}

sealed class GroupsUiState {
    object Loading : GroupsUiState()
    object Success : GroupsUiState()
    object Empty : GroupsUiState()
    data class Error(val message: String) : GroupsUiState()
}
