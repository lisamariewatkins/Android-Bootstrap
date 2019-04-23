package com.watkins.lisa.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author Lisa Watkins
 *
 * Serves as the main access point for underlying persisted relational database. Room provides an abstraction over SQLite to
 * allow easy database access.
 *
 * Since creating a [RoomDatabase] instance is fairly expensive, you should use a singleton design pattern to ensure only
 * one instance is created for the app's process. Ours is provided through Dagger with a @Singleton annotation.
 */
// TODO: Add your real entitites and export schema if you wish
@Database(entities = [FakeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
}