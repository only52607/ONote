package com.ooooonly.onote.model.entity

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.util.*

interface RoomEntity {
    var id: Long?
    var createTime: Date
    var modifyTime: Date

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateModifyTime() {
        modifyTime = Date.from(Instant.now())
    }
}