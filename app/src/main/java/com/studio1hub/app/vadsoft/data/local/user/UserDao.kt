package com.studio1hub.app.vadsoft.data.local.user

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.studio1hub.app.vadsoft.model.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(posts: List<User>)

    @Query("DELETE FROM User")
    fun deleteUsers()

    @Query("SELECT * FROM User WHERE id = :userId")
    fun getUserById(userId: Int): Flow<User>

    @Query("SELECT * FROM User")
    fun getAllUsers(): Flow<List<User>>
}
