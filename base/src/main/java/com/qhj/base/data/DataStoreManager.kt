package com.qhj.base.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

/**
 * Created by 7in on 2021-09-28 14:35
 */
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "ds_test")
class DataStoreManager private constructor(){
    private lateinit var dataStore: DataStore<Preferences>

    companion object{
        val instance: DataStoreManager by lazy(LazyThreadSafetyMode.SYNCHRONIZED){ DataStoreManager() }
    }

    fun init(context: Context){
        dataStore = context.dataStore
    }

    suspend fun putStringData(key: String, value: String){
        dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    fun getStringData(key: String, default: String = ""): Flow<String>{
        return dataStore.data.catch {
            it.printStackTrace()
            throw it
        }.map {
            it[stringPreferencesKey(key)]?:default
        }
    }
}