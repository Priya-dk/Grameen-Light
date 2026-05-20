package com.grameen.light.core

import android.content.Context
import androidx.room.Room
import com.grameen.light.core.data.NetworkMonitor
import com.grameen.light.core.local.AppDatabase
import com.grameen.light.core.repository.AuthRepository
import com.grameen.light.core.repository.ComplaintRepository
import com.grameen.light.core.repository.PoleRepository

object AppContainer {
    @Volatile
    private var initialized = false

    lateinit var authRepository: AuthRepository
        private set
    lateinit var poleRepository: PoleRepository
        private set
    lateinit var complaintRepository: ComplaintRepository
        private set
    lateinit var networkMonitor: NetworkMonitor
        private set

    fun init(context: Context) {
        if (initialized) return
        synchronized(this) {
            if (initialized) return

            val db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "grameen_light.db"
            ).fallbackToDestructiveMigration().build()

            authRepository = AuthRepository()
            poleRepository = PoleRepository(poleDao = db.poleDao())
            complaintRepository = ComplaintRepository(
                complaintDao = db.complaintDao(),
                poleRepository = poleRepository
            )
            networkMonitor = NetworkMonitor(context.applicationContext)
            initialized = true
        }
    }
}
