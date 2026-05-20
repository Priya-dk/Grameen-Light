package com.grameen.light.core.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.grameen.light.core.local.dao.ComplaintDao;
import com.grameen.light.core.local.dao.ComplaintDao_Impl;
import com.grameen.light.core.local.dao.PoleDao;
import com.grameen.light.core.local.dao.PoleDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile PoleDao _poleDao;

  private volatile ComplaintDao _complaintDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `poles` (`poleId` TEXT NOT NULL, `sector` TEXT NOT NULL, `controller` TEXT NOT NULL, `latitude` REAL NOT NULL, `longitude` REAL NOT NULL, `status` TEXT NOT NULL, `lastReportedMillis` INTEGER NOT NULL, PRIMARY KEY(`poleId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `complaints` (`complaintId` TEXT NOT NULL, `poleId` TEXT NOT NULL, `userId` TEXT NOT NULL, `issueType` TEXT NOT NULL, `description` TEXT NOT NULL, `photoUrl` TEXT, `status` TEXT NOT NULL, `assignedWorker` TEXT, `createdAtMillis` INTEGER NOT NULL, `updatedAtMillis` INTEGER NOT NULL, `synced` INTEGER NOT NULL, PRIMARY KEY(`complaintId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '638e21d63373c7b49374a134eacc6df6')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `poles`");
        db.execSQL("DROP TABLE IF EXISTS `complaints`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsPoles = new HashMap<String, TableInfo.Column>(7);
        _columnsPoles.put("poleId", new TableInfo.Column("poleId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPoles.put("sector", new TableInfo.Column("sector", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPoles.put("controller", new TableInfo.Column("controller", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPoles.put("latitude", new TableInfo.Column("latitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPoles.put("longitude", new TableInfo.Column("longitude", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPoles.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsPoles.put("lastReportedMillis", new TableInfo.Column("lastReportedMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPoles = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPoles = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPoles = new TableInfo("poles", _columnsPoles, _foreignKeysPoles, _indicesPoles);
        final TableInfo _existingPoles = TableInfo.read(db, "poles");
        if (!_infoPoles.equals(_existingPoles)) {
          return new RoomOpenHelper.ValidationResult(false, "poles(com.grameen.light.core.local.entity.PoleEntity).\n"
                  + " Expected:\n" + _infoPoles + "\n"
                  + " Found:\n" + _existingPoles);
        }
        final HashMap<String, TableInfo.Column> _columnsComplaints = new HashMap<String, TableInfo.Column>(11);
        _columnsComplaints.put("complaintId", new TableInfo.Column("complaintId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("poleId", new TableInfo.Column("poleId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("userId", new TableInfo.Column("userId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("issueType", new TableInfo.Column("issueType", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("photoUrl", new TableInfo.Column("photoUrl", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("status", new TableInfo.Column("status", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("assignedWorker", new TableInfo.Column("assignedWorker", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("createdAtMillis", new TableInfo.Column("createdAtMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("updatedAtMillis", new TableInfo.Column("updatedAtMillis", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsComplaints.put("synced", new TableInfo.Column("synced", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysComplaints = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesComplaints = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoComplaints = new TableInfo("complaints", _columnsComplaints, _foreignKeysComplaints, _indicesComplaints);
        final TableInfo _existingComplaints = TableInfo.read(db, "complaints");
        if (!_infoComplaints.equals(_existingComplaints)) {
          return new RoomOpenHelper.ValidationResult(false, "complaints(com.grameen.light.core.local.entity.ComplaintEntity).\n"
                  + " Expected:\n" + _infoComplaints + "\n"
                  + " Found:\n" + _existingComplaints);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "638e21d63373c7b49374a134eacc6df6", "19f8ec00cdfd0ae677cd7f8abf7d236e");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "poles","complaints");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `poles`");
      _db.execSQL("DELETE FROM `complaints`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(PoleDao.class, PoleDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ComplaintDao.class, ComplaintDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public PoleDao poleDao() {
    if (_poleDao != null) {
      return _poleDao;
    } else {
      synchronized(this) {
        if(_poleDao == null) {
          _poleDao = new PoleDao_Impl(this);
        }
        return _poleDao;
      }
    }
  }

  @Override
  public ComplaintDao complaintDao() {
    if (_complaintDao != null) {
      return _complaintDao;
    } else {
      synchronized(this) {
        if(_complaintDao == null) {
          _complaintDao = new ComplaintDao_Impl(this);
        }
        return _complaintDao;
      }
    }
  }
}
