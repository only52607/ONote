package com.ooooonly.onote.model.entity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "todo")
data class Todo constructor(
    @PrimaryKey(autoGenerate = true)
    override var id: Long? = null,
    var content: String,
    var done: Boolean = false,
    @ColumnInfo(name = "notify_cron")
    var notifyCron: String? = null,
    @ColumnInfo(name = "create_time")
    override var createTime: Date = Date.from(Instant.now()),
    @ColumnInfo(name = "modify_time")
    override var modifyTime: Date = Date.from(Instant.now()),
): RoomEntity