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
import com.grameen.light.core.local.entity.PoleEntity;
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
public final class PoleDao_Impl implements PoleDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PoleEntity> __insertionAdapterOfPoleEntity;

  public PoleDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPoleEntity = new EntityInsertionAdapter<PoleEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `poles` (`poleId`,`sector`,`controller`,`latitude`,`longitude`,`status`,`lastReportedMillis`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final PoleEntity entity) {
        if (entity.getPoleId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getPoleId());
        }
        if (entity.getSector() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getSector());
        }
        if (entity.getController() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getController());
        }
        statement.bindDouble(4, entity.getLatitude());
        statement.bindDouble(5, entity.getLongitude());
        if (entity.getStatus() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getStatus());
        }
        statement.bindLong(7, entity.getLastReportedMillis());
      }
    };
  }

  @Override
  public Object upsertAll(final List<PoleEntity> items,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPoleEntity.insert(items);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<PoleEntity>> observeAll() {
    final String _sql = "SELECT * FROM poles";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"poles"}, new Callable<List<PoleEntity>>() {
      @Override
      @NonNull
      public List<PoleEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPoleId = CursorUtil.getColumnIndexOrThrow(_cursor, "poleId");
          final int _cursorIndexOfSector = CursorUtil.getColumnIndexOrThrow(_cursor, "sector");
          final int _cursorIndexOfController = CursorUtil.getColumnIndexOrThrow(_cursor, "controller");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfLastReportedMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "lastReportedMillis");
          final List<PoleEntity> _result = new ArrayList<PoleEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final PoleEntity _item;
            final String _tmpPoleId;
            if (_cursor.isNull(_cursorIndexOfPoleId)) {
              _tmpPoleId = null;
            } else {
              _tmpPoleId = _cursor.getString(_cursorIndexOfPoleId);
            }
            final String _tmpSector;
            if (_cursor.isNull(_cursorIndexOfSector)) {
              _tmpSector = null;
            } else {
              _tmpSector = _cursor.getString(_cursorIndexOfSector);
            }
            final String _tmpController;
            if (_cursor.isNull(_cursorIndexOfController)) {
              _tmpController = null;
            } else {
              _tmpController = _cursor.getString(_cursorIndexOfController);
            }
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpLastReportedMillis;
            _tmpLastReportedMillis = _cursor.getLong(_cursorIndexOfLastReportedMillis);
            _item = new PoleEntity(_tmpPoleId,_tmpSector,_tmpController,_tmpLatitude,_tmpLongitude,_tmpStatus,_tmpLastReportedMillis);
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
  public Object getById(final String poleId, final Continuation<? super PoleEntity> $completion) {
    final String _sql = "SELECT * FROM poles WHERE poleId = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (poleId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, poleId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PoleEntity>() {
      @Override
      @Nullable
      public PoleEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPoleId = CursorUtil.getColumnIndexOrThrow(_cursor, "poleId");
          final int _cursorIndexOfSector = CursorUtil.getColumnIndexOrThrow(_cursor, "sector");
          final int _cursorIndexOfController = CursorUtil.getColumnIndexOrThrow(_cursor, "controller");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfLastReportedMillis = CursorUtil.getColumnIndexOrThrow(_cursor, "lastReportedMillis");
          final PoleEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpPoleId;
            if (_cursor.isNull(_cursorIndexOfPoleId)) {
              _tmpPoleId = null;
            } else {
              _tmpPoleId = _cursor.getString(_cursorIndexOfPoleId);
            }
            final String _tmpSector;
            if (_cursor.isNull(_cursorIndexOfSector)) {
              _tmpSector = null;
            } else {
              _tmpSector = _cursor.getString(_cursorIndexOfSector);
            }
            final String _tmpController;
            if (_cursor.isNull(_cursorIndexOfController)) {
              _tmpController = null;
            } else {
              _tmpController = _cursor.getString(_cursorIndexOfController);
            }
            final double _tmpLatitude;
            _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            final double _tmpLongitude;
            _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            final long _tmpLastReportedMillis;
            _tmpLastReportedMillis = _cursor.getLong(_cursorIndexOfLastReportedMillis);
            _result = new PoleEntity(_tmpPoleId,_tmpSector,_tmpController,_tmpLatitude,_tmpLongitude,_tmpStatus,_tmpLastReportedMillis);
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
