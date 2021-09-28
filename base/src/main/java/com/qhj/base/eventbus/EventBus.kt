package com.qhj.base.eventbus

import androidx.lifecycle.*
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by 7in on 2021-09-28 11:41
 */
class EventBus private constructor() : ViewModel(){
    private val map = mutableMapOf<String,Event<*>>()

    companion object{
        val instance: EventBus by lazy(LazyThreadSafetyMode.SYNCHRONIZED) { EventBus() }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> with(key: String) : Event<T>{
        var event = map[key]
        if (event==null){
            event = Event<T>(key)
            map[key] = event
        }
        return event as Event<T>
    }

    inner class Event<T>(private val key: String) : LifecycleObserver{
        private val flow = MutableSharedFlow<T>()

        fun register(lifecycleOwner: LifecycleOwner, action: (t: T) -> Unit){
            lifecycleOwner.lifecycle.addObserver(this)
            lifecycleOwner.lifecycleScope.launch {
                flow.collect {
                    action(it)
                }
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun unRegister(){
            val subscriptionCount = flow.subscriptionCount.value
            if (subscriptionCount<=0){
                map.remove(key)
            }
        }

        fun post(data: T){
            viewModelScope.launch {
                flow.emit(data)
            }
        }
    }

}
