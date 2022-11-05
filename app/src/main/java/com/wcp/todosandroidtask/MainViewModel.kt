package com.wcp.todosandroidtask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wcp.domain.model.ToDoModel
import com.wcp.domain.usecase.FetchToDos
import com.wcp.domain.usecase.GetToDos
import com.wcp.todosandroidtask.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    getToDos: GetToDos,
    private val fetchToDos: FetchToDos,
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiState<List<ToDoModel>>())
    val uiState: StateFlow<UiState<List<ToDoModel>>> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getToDos.invoke().collectLatest {
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        throwable = it.throwable,
                        data = it.data
                    )
                }
            }
        }
    }

    fun errorMessageShown() {
        _uiState.update { currentState ->
            currentState.copy(throwable = null)
        }
    }

    fun fetchTodos() {
        viewModelScope.launch {
            fetchToDos.invoke(true)
        }
    }
}
