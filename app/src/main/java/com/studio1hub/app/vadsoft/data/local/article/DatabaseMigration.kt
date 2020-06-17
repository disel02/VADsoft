package com.studio1hub.app.vadsoft.data.local.article

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object DatabaseMigration{

    const val DB_VERSION= 2

    val MIGRATION: Array<Migration>
    get() = arrayOf(
        migration12()
    )

    private fun migration12(): Migration = object: Migration(1,2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("ALTER TABLE Article ADD COLUMN body TEXT")
        }

    }
}
