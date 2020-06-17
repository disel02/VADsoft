package com.studio1hub.app.vadsoft.data.local.article

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.studio1hub.app.vadsoft.model.Article

@Database(
    entities = [(Article::class)],
    version = DatabaseMigration.DB_VERSION
)
abstract class ArticlePostDatabase: RoomDatabase(){

    abstract fun getArticleDao(): ArticleDao

    companion object{

       private const val DB_NAME = "articleDB"

        @Volatile
        var INSTANCE: ArticlePostDatabase? = null

        fun getInstance(context: Context): ArticlePostDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance!=null)
                return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticlePostDatabase::class.java,
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
