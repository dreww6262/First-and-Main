package com.example.firstandmainwilliamson

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ViewModel(application: Application): AndroidViewModel(application){
    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob+Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)

    private var disposable: Disposable? = null

    private val repository: StoreItemRepository = StoreItemRepository(StoreRoomDatabase.getDatabase(application).storeDao())

    val allStores: LiveData<List<StoreItem>>

    init {
        allStores = repository.allStores
    }

    fun refreshMovies(page: Int) {
/*
        disposable =
            RetrofitService.create("https://api.themoviedb.org/3/").getNowPlaying("2c74cdeba02dd3764bd74cd034284e4a",page).subscribeOn(
                Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(
                {result -> showResult(result)},
                {error -> showError(error)})
 */
    }

    private fun showError(error: Throwable?) {
        // handle error
    }

    private fun showResult(stores: Stores?) {
        deleteAll()
        stores?.results?.forEach { store ->
            insert(store)
        }
    }
    private fun insert(store: StoreItem) = scope.launch(Dispatchers.IO) {
        repository.insert(store)
    }
    private fun deleteAll() = scope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }

}