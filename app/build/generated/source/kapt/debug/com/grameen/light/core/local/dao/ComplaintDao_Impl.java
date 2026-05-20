package com.grameen.light.core.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.grameen.light.core.local.entity.ComplaintEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ComplaintDao_Impl implements ComplaintDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ComplaintEntity> __insertionAdapterOfComplaintEntity;

  public ComplaintDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfComplaintEntity = new EntityInsertionAdapter<ComplaintEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `complaints` (`complaintId`,`poleId`,`userId`,`issueType`,`description`,`photoUrl`,`status`,`assignedWorker`,`createdAtMillis`,`updatedAtMillis`,`synced`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ComplaintEntity entity) {
        if (entity.getComplaintId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getComplaintId());
        }
        if (entity.getPoleId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getPoleId());
        }
        if (entity.getUserId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getUserId());
        }
        if (entity.getIssueType() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getIssueType());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getDescription());
        }
        if (entity.getPhotoUrl() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPhotoUrl());
        }
        if (entity.getStatus() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getStatus());
        }
        if (entity.getAssignedWorker() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getAssignedWorker());
        }
        statement.bindLong(9, entity.getCreatedAtMillis());
        statement.bindLong(10, entity.getUpdatedAtMillis());
        final int _tmp = entity.getSynced() ? 1 : 0;
        statement.bindLong(11, _tmp);
      }
    };
  }

  @Override
  public Object upsert(final ComplaintEntity item, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfComplaintEntity.insert(item);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsertAll(final List<ComplaintEntity> items,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfComplaintEntity.insert(items);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ComplaintEntity>> observeByUser(final String userId) {
    final String _sql = "SELECT * FROM complaints WHERE userId = ? ORDER BY createdAtMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"complaints"}, new Callable<List<ComplaintEntity>>() {
      @Override
      @NonNull
      public List<ComplaintEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfComplaintId = CursorUtil.getColumnIndexOrThrow(_cursor, "complaintId");
          final int _cursorIndexOfPoleId = CursorUtil.getColumnIndexOrThrow(_cursor, "poleId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfIssueType = CursorUtil.getColumnIndexOrThrow(_cursor, "issueType");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPhotoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "photoUrl");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfAssignedWorker = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedWorker");
          final int _cursorIndexOfCreatedAtMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtMillis");
          final int _cursorIndexOfUpdatedAtMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAtMillis");
          final int _cursorIndexOfSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "synced");
          final List<ComplaintEntity> _result = new ArrayList<ComplaintEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ComplaintEntity _item;
            final String _tmpComplaintId;
            if (_cursor.isNull(_cursorIndexOfComplaintId)) {
              _tmpComplaintId = null;
            } else {
              _tmpComplaintId = _cursor.getString(_cursorIndexOfComplaintId);
            }
            final String _tmpPoleId;
            if (_cursor.isNull(_cursorIndexOfPoleId)) {
              _tmpPoleId = null;
            } else {
              _tmpPoleId = _cursor.getString(_cursorIndexOfPoleId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpIssueType;
            if (_cursor.isNull(_cursorIndexOfIssueType)) {
              _tmpIssueType = null;
            } else {
              _tmpIssueType = _cursor.getString(_cursorIndexOfIssueType);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpPhotoUrl;
            if (_cursor.isNull(_cursorIndexOfPhotoUrl)) {
              _tmpPhotoUrl = null;
            } else {
              _tmpPhotoUrl = _cursor.getString(_cursorIndexOfPhotoUrl);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final String _tmpAssignedWorker;
            if (_cursor.isNull(_cursorIndexOfAssignedWorker)) {
              _tmpAssignedWorker = null;
            } else {
              _tmpAssignedWorker = _cursor.getString(_cursorIndexOfAssignedWorker);
            }
            final long _tmpCreatedAtMillis;
            _tmpCreatedAtMillis = _cursor.getLong(_cursorIndexOfCreatedAtMillis);
            final long _tmpUpdatedAtMillis;
            _tmpUpdatedAtMillis = _cursor.getLong(_cursorIndexOfUpdatedAtMillis);
            final boolean _tmpSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSynced);
            _tmpSynced = _tmp != 0;
            _item = new ComplaintEntity(_tmpComplaintId,_tmpPoleId,_tmpUserId,_tmpIssueType,_tmpDescription,_tmpPhotoUrl,_tmpStatus,_tmpAssignedWorker,_tmpCreatedAtMillis,_tmpUpdatedAtMillis,_tmpSynced);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<ComplaintEntity>> observeAll() {
    final String _sql = "SELECT * FROM complaints ORDER BY createdAtMillis DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"complaints"}, new Callable<List<ComplaintEntity>>() {
      @Override
      @NonNull
      public List<ComplaintEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfComplaintId = CursorUtil.getColumnIndexOrThrow(_cursor, "complaintId");
          final int _cursorIndexOfPoleId = CursorUtil.getColumnIndexOrThrow(_cursor, "poleId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfIssueType = CursorUtil.getColumnIndexOrThrow(_cursor, "issueType");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPhotoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "photoUrl");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfAssignedWorker = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedWorker");
          final int _cursorIndexOfCreatedAtMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtMillis");
          final int _cursorIndexOfUpdatedAtMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAtMillis");
          final int _cursorIndexOfSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "synced");
          final List<ComplaintEntity> _result = new ArrayList<ComplaintEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ComplaintEntity _item;
            final String _tmpComplaintId;
            if (_cursor.isNull(_cursorIndexOfComplaintId)) {
              _tmpComplaintId = null;
            } else {
              _tmpComplaintId = _cursor.getString(_cursorIndexOfComplaintId);
            }
            final String _tmpPoleId;
            if (_cursor.isNull(_cursorIndexOfPoleId)) {
              _tmpPoleId = null;
            } else {
              _tmpPoleId = _cursor.getString(_cursorIndexOfPoleId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpIssueType;
            if (_cursor.isNull(_cursorIndexOfIssueType)) {
              _tmpIssueType = null;
            } else {
              _tmpIssueType = _cursor.getString(_cursorIndexOfIssueType);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpPhotoUrl;
            if (_cursor.isNull(_cursorIndexOfPhotoUrl)) {
              _tmpPhotoUrl = null;
            } else {
              _tmpPhotoUrl = _cursor.getString(_cursorIndexOfPhotoUrl);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final String _tmpAssignedWorker;
            if (_cursor.isNull(_cursorIndexOfAssignedWorker)) {
              _tmpAssignedWorker = null;
            } else {
              _tmpAssignedWorker = _cursor.getString(_cursorIndexOfAssignedWorker);
            }
            final long _tmpCreatedAtMillis;
            _tmpCreatedAtMillis = _cursor.getLong(_cursorIndexOfCreatedAtMillis);
            final long _tmpUpdatedAtMillis;
            _tmpUpdatedAtMillis = _cursor.getLong(_cursorIndexOfUpdatedAtMillis);
            final boolean _tmpSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSynced);
            _tmpSynced = _tmp != 0;
            _item = new ComplaintEntity(_tmpComplaintId,_tmpPoleId,_tmpUserId,_tmpIssueType,_tmpDescription,_tmpPhotoUrl,_tmpStatus,_tmpAssignedWorker,_tmpCreatedAtMillis,_tmpUpdatedAtMillis,_tmpSynced);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getUnsynced(final Continuation<? super List<ComplaintEntity>> $completion) {
    final String _sql = "SELECT * FROM complaints WHERE synced = 0";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<ComplaintEntity>>() {
      @Override
      @NonNull
      public List<ComplaintEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfComplaintId = CursorUtil.getColumnIndexOrThrow(_cursor, "complaintId");
          final int _cursorIndexOfPoleId = CursorUtil.getColumnIndexOrThrow(_cursor, "poleId");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfIssueType = CursorUtil.getColumnIndexOrThrow(_cursor, "issueType");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfPhotoUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "photoUrl");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfAssignedWorker = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedWorker");
          final int _cursorIndexOfCreatedAtMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAtMillis");
          final int _cursorIndexOfUpdatedAtMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAtMillis");
          final int _cursorIndexOfSynced = CursorUtil.getColumnIndexOrThrow(_cursor, "synced");
          final List<ComplaintEntity> _result = new ArrayList<ComplaintEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ComplaintEntity _item;
            final String _tmpComplaintId;
            if (_cursor.isNull(_cursorIndexOfComplaintId)) {
              _tmpComplaintId = null;
            } else {
              _tmpComplaintId = _cursor.getString(_cursorIndexOfComplaintId);
            }
            final String _tmpPoleId;
            if (_cursor.isNull(_cursorIndexOfPoleId)) {
              _tmpPoleId = null;
            } else {
              _tmpPoleId = _cursor.getString(_cursorIndexOfPoleId);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpIssueType;
            if (_cursor.isNull(_cursorIndexOfIssueType)) {
              _tmpIssueType = null;
            } else {
              _tmpIssueType = _cursor.getString(_cursorIndexOfIssueType);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final String _tmpPhotoUrl;
            if (_cursor.isNull(_cursorIndexOfPhotoUrl)) {
              _tmpPhotoUrl = null;
            } else {
              _tmpPhotoUrl = _cursor.getString(_cursorIndexOfPhotoUrl);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final String _tmpAssignedWorker;
            if (_cursor.isNull(_cursorIndexOfAssignedWorker)) {
              _tmpAssignedWorker = null;
            } else {
              _tmpAssignedWorker = _cursor.getString(_cursorIndexOfAssignedWorker);
            }
            final long _tmpCreatedAtMillis;
            _tmpCreatedAtMillis = _cursor.getLong(_cursorIndexOfCreatedAtMillis);
            final long _tmpUpdatedAtMillis;
            _tmpUpdatedAtMillis = _cursor.getLong(_cursorIndexOfUpdatedAtMillis);
            final boolean _tmpSynced;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfSynced);
            _tmpSynced = _tmp != 0;
            _item = new ComplaintEntity(_tmpComplaintId,_tmpPoleId,_tmpUserId,_tmpIssueType,_tmpDescription,_tmpPhotoUrl,_tmpStatus,_tmpAssignedWorker,_tmpCreatedAtMillis,_tmpUpdatedAtMillis,_tmpSynced);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getLatestComplaintId(final Continuation<? super String> $completion) {
    final String _sql = "SELECT complaintId FROM complaints ORDER BY complaintId DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<String>() {
      @Override
      @Nullable
      public String call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final String _result;
          if (_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getString(0);
            }
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
