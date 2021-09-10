package com.ooooonly.onote.model.entity

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
@Entity(tableName = "note_package")
data class NotePackage (
    @PrimaryKey(autoGenerate = true)
    override var id: Long? = null,
    var name: String = "DEFAULT",
    var type: NotePackageType = NotePackageType.PUBLIC,
    var password: String? = null,
    var cover: Int? = null,
    @ColumnInfo(name = "is_all")
    val isAll: Boolean = false,         // 代表该NotePackage包含所有的Note
    @ColumnInfo(name = "create_time")
    override var createTime: Date = Date.from(Instant.now()),
    @ColumnInfo(name = "modify_time")
    override var modifyTime: Date = Date.from(Instant.now()),
): RoomEntity {
    companion object {
        val ALL: NotePackage = NotePackage(name = "全部", isAll = true)
    }
}

enum class NotePackageType {
    PUBLIC, ENCRYPT
}