package com.studio1hub.app.vadsoft.data.local.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.studio1hub.app.vadsoft.model.User

@Database(
    entities = [(User::class)],
    version = DatabaseMigration.DB_VERSION
)
abstract class UserPostDatabase: RoomDatabase(){

    abstract fun getUserDao(): UserDao

    companion object{

       const val DB_NAME = "userDB"

        @Volatile
        var INSTANCE: UserPostDatabase? = null

        fun getInstance(context: Context):UserPostDatabase{
            val tempInstance = INSTANCE
            if (tempInstance!=null)
                return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserPostDatabase::class.java,
                    DB_NAME
                )
                    .addMigrations(*DatabaseMigration.MIGRATION)
                    .build()

                INSTANCE = instance
                return instance

            }
        }
    }
}
