package com.isabelcmdcosta.listgists.data.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.isabelcmdcosta.listgists.data.models.Gist
import com.isabelcmdcosta.listgists.data.models.GistSimplified
import io.reactivex.Single
import java.util.*

@Dao
interface GistDao {
    @Query("SELECT * FROM gist")
    fun getAll(): Single<List<GistSimplified>>

    @Insert(onConflict = REPLACE)
    fun insertAll(gists: List<GistSimplified>)

    @Insert(onConflict = REPLACE)
    fun insert(gist: GistSimplified)

    @Query("DELETE FROM gist")
    fun deleteAll()

    @Delete
    fun delete(gist: Gist)
}