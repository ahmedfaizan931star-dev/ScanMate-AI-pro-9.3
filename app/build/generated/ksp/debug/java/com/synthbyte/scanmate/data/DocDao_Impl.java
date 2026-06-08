package com.synthbyte.scanmate.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.RelationUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
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
public final class DocDao_Impl implements DocDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Document> __insertionAdapterOfDocument;

  private final EntityInsertionAdapter<Page> __insertionAdapterOfPage;

  private final EntityInsertionAdapter<QrHistory> __insertionAdapterOfQrHistory;

  private final EntityDeletionOrUpdateAdapter<Document> __updateAdapterOfDocument;

  private final EntityDeletionOrUpdateAdapter<Page> __updateAdapterOfPage;

  private final SharedSQLiteStatement __preparedStmtOfRenameDocument;

  private final SharedSQLiteStatement __preparedStmtOfSetFavorite;

  private final SharedSQLiteStatement __preparedStmtOfSetPinned;

  private final SharedSQLiteStatement __preparedStmtOfUpdateOcrText;

  private final SharedSQLiteStatement __preparedStmtOfUpdateCategoryTags;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDocumentById;

  private final SharedSQLiteStatement __preparedStmtOfUpdatePageImage;

  private final SharedSQLiteStatement __preparedStmtOfUpdatePageOrder;

  private final SharedSQLiteStatement __preparedStmtOfDeletePageById;

  private final SharedSQLiteStatement __preparedStmtOfClearQrHistory;

  public DocDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDocument = new EntityInsertionAdapter<Document>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `documents` (`id`,`title`,`timestamp`,`updatedAt`,`type`,`isArchived`,`isFavorite`,`isPinned`,`workspace`,`ocrText`,`category`,`tags`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Document entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindLong(3, entity.getTimestamp());
        statement.bindLong(4, entity.getUpdatedAt());
        statement.bindString(5, entity.getType());
        final int _tmp = entity.isArchived() ? 1 : 0;
        statement.bindLong(6, _tmp);
        final int _tmp_1 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        final int _tmp_2 = entity.isPinned() ? 1 : 0;
        statement.bindLong(8, _tmp_2);
        statement.bindString(9, entity.getWorkspace());
        if (entity.getOcrText() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getOcrText());
        }
        statement.bindString(11, entity.getCategory());
        statement.bindString(12, entity.getTags());
      }
    };
    this.__insertionAdapterOfPage = new EntityInsertionAdapter<Page>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `pages` (`id`,`documentId`,`imagePath`,`pageOrder`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Page entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDocumentId());
        statement.bindString(3, entity.getImagePath());
        statement.bindLong(4, entity.getPageOrder());
      }
    };
    this.__insertionAdapterOfQrHistory = new EntityInsertionAdapter<QrHistory>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `qr_history` (`id`,`value`,`type`,`timestamp`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final QrHistory entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getValue());
        statement.bindString(3, entity.getType());
        statement.bindLong(4, entity.getTimestamp());
      }
    };
    this.__updateAdapterOfDocument = new EntityDeletionOrUpdateAdapter<Document>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `documents` SET `id` = ?,`title` = ?,`timestamp` = ?,`updatedAt` = ?,`type` = ?,`isArchived` = ?,`isFavorite` = ?,`isPinned` = ?,`workspace` = ?,`ocrText` = ?,`category` = ?,`tags` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Document entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getTitle());
        statement.bindLong(3, entity.getTimestamp());
        statement.bindLong(4, entity.getUpdatedAt());
        statement.bindString(5, entity.getType());
        final int _tmp = entity.isArchived() ? 1 : 0;
        statement.bindLong(6, _tmp);
        final int _tmp_1 = entity.isFavorite() ? 1 : 0;
        statement.bindLong(7, _tmp_1);
        final int _tmp_2 = entity.isPinned() ? 1 : 0;
        statement.bindLong(8, _tmp_2);
        statement.bindString(9, entity.getWorkspace());
        if (entity.getOcrText() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getOcrText());
        }
        statement.bindString(11, entity.getCategory());
        statement.bindString(12, entity.getTags());
        statement.bindLong(13, entity.getId());
      }
    };
    this.__updateAdapterOfPage = new EntityDeletionOrUpdateAdapter<Page>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `pages` SET `id` = ?,`documentId` = ?,`imagePath` = ?,`pageOrder` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Page entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDocumentId());
        statement.bindString(3, entity.getImagePath());
        statement.bindLong(4, entity.getPageOrder());
        statement.bindLong(5, entity.getId());
      }
    };
    this.__preparedStmtOfRenameDocument = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE documents SET title = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetFavorite = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE documents SET isFavorite = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetPinned = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE documents SET isPinned = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateOcrText = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE documents SET ocrText = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateCategoryTags = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE documents SET category = ?, tags = ?, workspace = ?, updatedAt = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteDocumentById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM documents WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdatePageImage = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE pages SET imagePath = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdatePageOrder = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE pages SET pageOrder = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeletePageById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM pages WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearQrHistory = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM qr_history";
        return _query;
      }
    };
  }

  @Override
  public Object insertDocument(final Document document,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfDocument.insertAndReturnId(document);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertPage(final Page page, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfPage.insertAndReturnId(page);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertQrHistory(final QrHistory item,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfQrHistory.insertAndReturnId(item);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateDocument(final Document document,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfDocument.handle(document);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePage(final Page page, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfPage.handle(page);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object renameDocument(final long id, final String title, final long updatedAt,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfRenameDocument.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, title);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, updatedAt);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfRenameDocument.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object setFavorite(final long id, final boolean isFavorite, final long updatedAt,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetFavorite.acquire();
        int _argIndex = 1;
        final int _tmp = isFavorite ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, updatedAt);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfSetFavorite.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object setPinned(final long id, final boolean isPinned, final long updatedAt,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetPinned.acquire();
        int _argIndex = 1;
        final int _tmp = isPinned ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, updatedAt);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfSetPinned.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateOcrText(final long id, final String ocrText, final long updatedAt,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateOcrText.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, ocrText);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, updatedAt);
        _argIndex = 3;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateOcrText.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updateCategoryTags(final long id, final String category, final String tags,
      final String workspace, final long updatedAt, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateCategoryTags.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, category);
        _argIndex = 2;
        _stmt.bindString(_argIndex, tags);
        _argIndex = 3;
        _stmt.bindString(_argIndex, workspace);
        _argIndex = 4;
        _stmt.bindLong(_argIndex, updatedAt);
        _argIndex = 5;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdateCategoryTags.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteDocumentById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDocumentById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteDocumentById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePageImage(final long id, final String imagePath,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdatePageImage.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, imagePath);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdatePageImage.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object updatePageOrder(final long id, final int pageOrder,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdatePageOrder.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, pageOrder);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfUpdatePageOrder.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deletePageById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeletePageById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeletePageById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearQrHistory(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearQrHistory.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfClearQrHistory.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Document>> getAllDocuments() {
    final String _sql = "SELECT * FROM documents ORDER BY isPinned DESC, updatedAt DESC, timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"documents"}, new Callable<List<Document>>() {
      @Override
      @NonNull
      public List<Document> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfWorkspace = CursorUtil.getColumnIndexOrThrow(_cursor, "workspace");
          final int _cursorIndexOfOcrText = CursorUtil.getColumnIndexOrThrow(_cursor, "ocrText");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final List<Document> _result = new ArrayList<Document>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Document _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final boolean _tmpIsArchived;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsPinned;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp_2 != 0;
            final String _tmpWorkspace;
            _tmpWorkspace = _cursor.getString(_cursorIndexOfWorkspace);
            final String _tmpOcrText;
            if (_cursor.isNull(_cursorIndexOfOcrText)) {
              _tmpOcrText = null;
            } else {
              _tmpOcrText = _cursor.getString(_cursorIndexOfOcrText);
            }
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpTags;
            _tmpTags = _cursor.getString(_cursorIndexOfTags);
            _item = new Document(_tmpId,_tmpTitle,_tmpTimestamp,_tmpUpdatedAt,_tmpType,_tmpIsArchived,_tmpIsFavorite,_tmpIsPinned,_tmpWorkspace,_tmpOcrText,_tmpCategory,_tmpTags);
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
  public Flow<List<Document>> getFavoriteDocuments() {
    final String _sql = "SELECT * FROM documents WHERE isFavorite = 1 ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"documents"}, new Callable<List<Document>>() {
      @Override
      @NonNull
      public List<Document> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfWorkspace = CursorUtil.getColumnIndexOrThrow(_cursor, "workspace");
          final int _cursorIndexOfOcrText = CursorUtil.getColumnIndexOrThrow(_cursor, "ocrText");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final List<Document> _result = new ArrayList<Document>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Document _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final boolean _tmpIsArchived;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsPinned;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp_2 != 0;
            final String _tmpWorkspace;
            _tmpWorkspace = _cursor.getString(_cursorIndexOfWorkspace);
            final String _tmpOcrText;
            if (_cursor.isNull(_cursorIndexOfOcrText)) {
              _tmpOcrText = null;
            } else {
              _tmpOcrText = _cursor.getString(_cursorIndexOfOcrText);
            }
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpTags;
            _tmpTags = _cursor.getString(_cursorIndexOfTags);
            _item = new Document(_tmpId,_tmpTitle,_tmpTimestamp,_tmpUpdatedAt,_tmpType,_tmpIsArchived,_tmpIsFavorite,_tmpIsPinned,_tmpWorkspace,_tmpOcrText,_tmpCategory,_tmpTags);
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
  public Flow<List<Document>> getPinnedDocuments() {
    final String _sql = "SELECT * FROM documents WHERE isPinned = 1 ORDER BY updatedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"documents"}, new Callable<List<Document>>() {
      @Override
      @NonNull
      public List<Document> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfWorkspace = CursorUtil.getColumnIndexOrThrow(_cursor, "workspace");
          final int _cursorIndexOfOcrText = CursorUtil.getColumnIndexOrThrow(_cursor, "ocrText");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final List<Document> _result = new ArrayList<Document>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Document _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final boolean _tmpIsArchived;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsPinned;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp_2 != 0;
            final String _tmpWorkspace;
            _tmpWorkspace = _cursor.getString(_cursorIndexOfWorkspace);
            final String _tmpOcrText;
            if (_cursor.isNull(_cursorIndexOfOcrText)) {
              _tmpOcrText = null;
            } else {
              _tmpOcrText = _cursor.getString(_cursorIndexOfOcrText);
            }
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpTags;
            _tmpTags = _cursor.getString(_cursorIndexOfTags);
            _item = new Document(_tmpId,_tmpTitle,_tmpTimestamp,_tmpUpdatedAt,_tmpType,_tmpIsArchived,_tmpIsFavorite,_tmpIsPinned,_tmpWorkspace,_tmpOcrText,_tmpCategory,_tmpTags);
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
  public Flow<List<Document>> getRecentDocuments(final int limit) {
    final String _sql = "SELECT * FROM documents ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"documents"}, new Callable<List<Document>>() {
      @Override
      @NonNull
      public List<Document> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfWorkspace = CursorUtil.getColumnIndexOrThrow(_cursor, "workspace");
          final int _cursorIndexOfOcrText = CursorUtil.getColumnIndexOrThrow(_cursor, "ocrText");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final List<Document> _result = new ArrayList<Document>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Document _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final boolean _tmpIsArchived;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsPinned;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp_2 != 0;
            final String _tmpWorkspace;
            _tmpWorkspace = _cursor.getString(_cursorIndexOfWorkspace);
            final String _tmpOcrText;
            if (_cursor.isNull(_cursorIndexOfOcrText)) {
              _tmpOcrText = null;
            } else {
              _tmpOcrText = _cursor.getString(_cursorIndexOfOcrText);
            }
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpTags;
            _tmpTags = _cursor.getString(_cursorIndexOfTags);
            _item = new Document(_tmpId,_tmpTitle,_tmpTimestamp,_tmpUpdatedAt,_tmpType,_tmpIsArchived,_tmpIsFavorite,_tmpIsPinned,_tmpWorkspace,_tmpOcrText,_tmpCategory,_tmpTags);
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
  public Object getRecentDocumentsOnce(final int limit,
      final Continuation<? super List<Document>> $completion) {
    final String _sql = "SELECT * FROM documents ORDER BY timestamp DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Document>>() {
      @Override
      @NonNull
      public List<Document> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfWorkspace = CursorUtil.getColumnIndexOrThrow(_cursor, "workspace");
          final int _cursorIndexOfOcrText = CursorUtil.getColumnIndexOrThrow(_cursor, "ocrText");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final List<Document> _result = new ArrayList<Document>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Document _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final boolean _tmpIsArchived;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsPinned;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp_2 != 0;
            final String _tmpWorkspace;
            _tmpWorkspace = _cursor.getString(_cursorIndexOfWorkspace);
            final String _tmpOcrText;
            if (_cursor.isNull(_cursorIndexOfOcrText)) {
              _tmpOcrText = null;
            } else {
              _tmpOcrText = _cursor.getString(_cursorIndexOfOcrText);
            }
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpTags;
            _tmpTags = _cursor.getString(_cursorIndexOfTags);
            _item = new Document(_tmpId,_tmpTitle,_tmpTimestamp,_tmpUpdatedAt,_tmpType,_tmpIsArchived,_tmpIsFavorite,_tmpIsPinned,_tmpWorkspace,_tmpOcrText,_tmpCategory,_tmpTags);
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
  public Flow<Integer> getPageCountFlow() {
    final String _sql = "SELECT COUNT(*) FROM pages";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"pages"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Flow<List<Page>> getAllPages() {
    final String _sql = "SELECT * FROM pages ORDER BY documentId ASC, pageOrder ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"pages"}, new Callable<List<Page>>() {
      @Override
      @NonNull
      public List<Page> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDocumentId = CursorUtil.getColumnIndexOrThrow(_cursor, "documentId");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final int _cursorIndexOfPageOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "pageOrder");
          final List<Page> _result = new ArrayList<Page>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Page _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDocumentId;
            _tmpDocumentId = _cursor.getLong(_cursorIndexOfDocumentId);
            final String _tmpImagePath;
            _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            final int _tmpPageOrder;
            _tmpPageOrder = _cursor.getInt(_cursorIndexOfPageOrder);
            _item = new Page(_tmpId,_tmpDocumentId,_tmpImagePath,_tmpPageOrder);
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
  public Flow<List<Page>> getFirstPagePerDocument() {
    final String _sql = "SELECT * FROM pages WHERE pageOrder = 0 ORDER BY documentId ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"pages"}, new Callable<List<Page>>() {
      @Override
      @NonNull
      public List<Page> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDocumentId = CursorUtil.getColumnIndexOrThrow(_cursor, "documentId");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final int _cursorIndexOfPageOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "pageOrder");
          final List<Page> _result = new ArrayList<Page>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Page _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDocumentId;
            _tmpDocumentId = _cursor.getLong(_cursorIndexOfDocumentId);
            final String _tmpImagePath;
            _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            final int _tmpPageOrder;
            _tmpPageOrder = _cursor.getInt(_cursorIndexOfPageOrder);
            _item = new Page(_tmpId,_tmpDocumentId,_tmpImagePath,_tmpPageOrder);
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
  public Object getPageCountForDocument(final long docId,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM pages WHERE documentId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, docId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Flow<Integer> getPdfCountFlow() {
    final String _sql = "SELECT COUNT(*) FROM documents WHERE type = 'PDF'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"documents"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Flow<List<Document>> searchDocuments(final String query) {
    final String _sql = "\n"
            + "        SELECT * FROM documents\n"
            + "        WHERE title LIKE '%' || ? || '%'\n"
            + "           OR IFNULL(ocrText, '') LIKE '%' || ? || '%'\n"
            + "           OR IFNULL(category, '') LIKE '%' || ? || '%'\n"
            + "           OR IFNULL(tags, '') LIKE '%' || ? || '%'\n"
            + "           OR IFNULL(workspace, '') LIKE '%' || ? || '%'\n"
            + "        ORDER BY updatedAt DESC, timestamp DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 5);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    _argIndex = 3;
    _statement.bindString(_argIndex, query);
    _argIndex = 4;
    _statement.bindString(_argIndex, query);
    _argIndex = 5;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"documents"}, new Callable<List<Document>>() {
      @Override
      @NonNull
      public List<Document> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfWorkspace = CursorUtil.getColumnIndexOrThrow(_cursor, "workspace");
          final int _cursorIndexOfOcrText = CursorUtil.getColumnIndexOrThrow(_cursor, "ocrText");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final List<Document> _result = new ArrayList<Document>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Document _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final boolean _tmpIsArchived;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsPinned;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp_2 != 0;
            final String _tmpWorkspace;
            _tmpWorkspace = _cursor.getString(_cursorIndexOfWorkspace);
            final String _tmpOcrText;
            if (_cursor.isNull(_cursorIndexOfOcrText)) {
              _tmpOcrText = null;
            } else {
              _tmpOcrText = _cursor.getString(_cursorIndexOfOcrText);
            }
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpTags;
            _tmpTags = _cursor.getString(_cursorIndexOfTags);
            _item = new Document(_tmpId,_tmpTitle,_tmpTimestamp,_tmpUpdatedAt,_tmpType,_tmpIsArchived,_tmpIsFavorite,_tmpIsPinned,_tmpWorkspace,_tmpOcrText,_tmpCategory,_tmpTags);
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
  public Flow<Document> getDocument(final long id) {
    final String _sql = "SELECT * FROM documents WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"documents"}, new Callable<Document>() {
      @Override
      @Nullable
      public Document call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
          final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
          final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
          final int _cursorIndexOfWorkspace = CursorUtil.getColumnIndexOrThrow(_cursor, "workspace");
          final int _cursorIndexOfOcrText = CursorUtil.getColumnIndexOrThrow(_cursor, "ocrText");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
          final Document _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final boolean _tmpIsArchived;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsArchived);
            _tmpIsArchived = _tmp != 0;
            final boolean _tmpIsFavorite;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
            _tmpIsFavorite = _tmp_1 != 0;
            final boolean _tmpIsPinned;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsPinned);
            _tmpIsPinned = _tmp_2 != 0;
            final String _tmpWorkspace;
            _tmpWorkspace = _cursor.getString(_cursorIndexOfWorkspace);
            final String _tmpOcrText;
            if (_cursor.isNull(_cursorIndexOfOcrText)) {
              _tmpOcrText = null;
            } else {
              _tmpOcrText = _cursor.getString(_cursorIndexOfOcrText);
            }
            final String _tmpCategory;
            _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
            final String _tmpTags;
            _tmpTags = _cursor.getString(_cursorIndexOfTags);
            _result = new Document(_tmpId,_tmpTitle,_tmpTimestamp,_tmpUpdatedAt,_tmpType,_tmpIsArchived,_tmpIsFavorite,_tmpIsPinned,_tmpWorkspace,_tmpOcrText,_tmpCategory,_tmpTags);
          } else {
            _result = null;
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
  public Flow<DocumentWithPages> getDocumentWithPages(final long id) {
    final String _sql = "SELECT * FROM documents WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, true, new String[] {"pages",
        "documents"}, new Callable<DocumentWithPages>() {
      @Override
      @Nullable
      public DocumentWithPages call() throws Exception {
        __db.beginTransaction();
        try {
          final Cursor _cursor = DBUtil.query(__db, _statement, true, null);
          try {
            final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
            final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
            final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
            final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
            final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
            final int _cursorIndexOfIsArchived = CursorUtil.getColumnIndexOrThrow(_cursor, "isArchived");
            final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
            final int _cursorIndexOfIsPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "isPinned");
            final int _cursorIndexOfWorkspace = CursorUtil.getColumnIndexOrThrow(_cursor, "workspace");
            final int _cursorIndexOfOcrText = CursorUtil.getColumnIndexOrThrow(_cursor, "ocrText");
            final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
            final int _cursorIndexOfTags = CursorUtil.getColumnIndexOrThrow(_cursor, "tags");
            final LongSparseArray<ArrayList<Page>> _collectionPages = new LongSparseArray<ArrayList<Page>>();
            while (_cursor.moveToNext()) {
              final long _tmpKey;
              _tmpKey = _cursor.getLong(_cursorIndexOfId);
              if (!_collectionPages.containsKey(_tmpKey)) {
                _collectionPages.put(_tmpKey, new ArrayList<Page>());
              }
            }
            _cursor.moveToPosition(-1);
            __fetchRelationshippagesAscomSynthbyteScanmateDataPage(_collectionPages);
            final DocumentWithPages _result;
            if (_cursor.moveToFirst()) {
              final Document _tmpDocument;
              final long _tmpId;
              _tmpId = _cursor.getLong(_cursorIndexOfId);
              final String _tmpTitle;
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
              final long _tmpTimestamp;
              _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
              final long _tmpUpdatedAt;
              _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
              final String _tmpType;
              _tmpType = _cursor.getString(_cursorIndexOfType);
              final boolean _tmpIsArchived;
              final int _tmp;
              _tmp = _cursor.getInt(_cursorIndexOfIsArchived);
              _tmpIsArchived = _tmp != 0;
              final boolean _tmpIsFavorite;
              final int _tmp_1;
              _tmp_1 = _cursor.getInt(_cursorIndexOfIsFavorite);
              _tmpIsFavorite = _tmp_1 != 0;
              final boolean _tmpIsPinned;
              final int _tmp_2;
              _tmp_2 = _cursor.getInt(_cursorIndexOfIsPinned);
              _tmpIsPinned = _tmp_2 != 0;
              final String _tmpWorkspace;
              _tmpWorkspace = _cursor.getString(_cursorIndexOfWorkspace);
              final String _tmpOcrText;
              if (_cursor.isNull(_cursorIndexOfOcrText)) {
                _tmpOcrText = null;
              } else {
                _tmpOcrText = _cursor.getString(_cursorIndexOfOcrText);
              }
              final String _tmpCategory;
              _tmpCategory = _cursor.getString(_cursorIndexOfCategory);
              final String _tmpTags;
              _tmpTags = _cursor.getString(_cursorIndexOfTags);
              _tmpDocument = new Document(_tmpId,_tmpTitle,_tmpTimestamp,_tmpUpdatedAt,_tmpType,_tmpIsArchived,_tmpIsFavorite,_tmpIsPinned,_tmpWorkspace,_tmpOcrText,_tmpCategory,_tmpTags);
              final ArrayList<Page> _tmpPagesCollection;
              final long _tmpKey_1;
              _tmpKey_1 = _cursor.getLong(_cursorIndexOfId);
              _tmpPagesCollection = _collectionPages.get(_tmpKey_1);
              _result = new DocumentWithPages(_tmpDocument,_tmpPagesCollection);
            } else {
              _result = null;
            }
            __db.setTransactionSuccessful();
            return _result;
          } finally {
            _cursor.close();
          }
        } finally {
          __db.endTransaction();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<Page> getPage(final long id) {
    final String _sql = "SELECT * FROM pages WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"pages"}, new Callable<Page>() {
      @Override
      @Nullable
      public Page call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDocumentId = CursorUtil.getColumnIndexOrThrow(_cursor, "documentId");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final int _cursorIndexOfPageOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "pageOrder");
          final Page _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDocumentId;
            _tmpDocumentId = _cursor.getLong(_cursorIndexOfDocumentId);
            final String _tmpImagePath;
            _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            final int _tmpPageOrder;
            _tmpPageOrder = _cursor.getInt(_cursorIndexOfPageOrder);
            _result = new Page(_tmpId,_tmpDocumentId,_tmpImagePath,_tmpPageOrder);
          } else {
            _result = null;
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
  public Object getPageOnce(final long id, final Continuation<? super Page> $completion) {
    final String _sql = "SELECT * FROM pages WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Page>() {
      @Override
      @Nullable
      public Page call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDocumentId = CursorUtil.getColumnIndexOrThrow(_cursor, "documentId");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final int _cursorIndexOfPageOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "pageOrder");
          final Page _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDocumentId;
            _tmpDocumentId = _cursor.getLong(_cursorIndexOfDocumentId);
            final String _tmpImagePath;
            _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            final int _tmpPageOrder;
            _tmpPageOrder = _cursor.getInt(_cursorIndexOfPageOrder);
            _result = new Page(_tmpId,_tmpDocumentId,_tmpImagePath,_tmpPageOrder);
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

  @Override
  public Flow<List<Page>> getPagesForDocument(final long docId) {
    final String _sql = "SELECT * FROM pages WHERE documentId = ? ORDER BY pageOrder ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, docId);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"pages"}, new Callable<List<Page>>() {
      @Override
      @NonNull
      public List<Page> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDocumentId = CursorUtil.getColumnIndexOrThrow(_cursor, "documentId");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final int _cursorIndexOfPageOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "pageOrder");
          final List<Page> _result = new ArrayList<Page>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Page _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDocumentId;
            _tmpDocumentId = _cursor.getLong(_cursorIndexOfDocumentId);
            final String _tmpImagePath;
            _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            final int _tmpPageOrder;
            _tmpPageOrder = _cursor.getInt(_cursorIndexOfPageOrder);
            _item = new Page(_tmpId,_tmpDocumentId,_tmpImagePath,_tmpPageOrder);
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
  public Object getPagesForDocumentOnce(final long docId,
      final Continuation<? super List<Page>> $completion) {
    final String _sql = "SELECT * FROM pages WHERE documentId = ? ORDER BY pageOrder ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, docId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Page>>() {
      @Override
      @NonNull
      public List<Page> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDocumentId = CursorUtil.getColumnIndexOrThrow(_cursor, "documentId");
          final int _cursorIndexOfImagePath = CursorUtil.getColumnIndexOrThrow(_cursor, "imagePath");
          final int _cursorIndexOfPageOrder = CursorUtil.getColumnIndexOrThrow(_cursor, "pageOrder");
          final List<Page> _result = new ArrayList<Page>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Page _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDocumentId;
            _tmpDocumentId = _cursor.getLong(_cursorIndexOfDocumentId);
            final String _tmpImagePath;
            _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
            final int _tmpPageOrder;
            _tmpPageOrder = _cursor.getInt(_cursorIndexOfPageOrder);
            _item = new Page(_tmpId,_tmpDocumentId,_tmpImagePath,_tmpPageOrder);
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
  public Flow<List<QrHistory>> getQrHistory() {
    final String _sql = "SELECT * FROM qr_history ORDER BY timestamp DESC LIMIT 50";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"qr_history"}, new Callable<List<QrHistory>>() {
      @Override
      @NonNull
      public List<QrHistory> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfValue = CursorUtil.getColumnIndexOrThrow(_cursor, "value");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final List<QrHistory> _result = new ArrayList<QrHistory>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final QrHistory _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpValue;
            _tmpValue = _cursor.getString(_cursorIndexOfValue);
            final String _tmpType;
            _tmpType = _cursor.getString(_cursorIndexOfType);
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            _item = new QrHistory(_tmpId,_tmpValue,_tmpType,_tmpTimestamp);
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
  public Object moveDocumentsToWorkspace(final List<Long> ids, final String workspace,
      final long updatedAt, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("UPDATE documents SET workspace = ");
        _stringBuilder.append("?");
        _stringBuilder.append(", updatedAt = ");
        _stringBuilder.append("?");
        _stringBuilder.append(" WHERE id IN (");
        final int _inputSize = ids.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
        int _argIndex = 1;
        _stmt.bindString(_argIndex, workspace);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, updatedAt);
        _argIndex = 3;
        for (long _item : ids) {
          _stmt.bindLong(_argIndex, _item);
          _argIndex++;
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object setFavoriteBulk(final List<Long> ids, final boolean isFavorite,
      final long updatedAt, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("UPDATE documents SET isFavorite = ");
        _stringBuilder.append("?");
        _stringBuilder.append(", updatedAt = ");
        _stringBuilder.append("?");
        _stringBuilder.append(" WHERE id IN (");
        final int _inputSize = ids.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
        int _argIndex = 1;
        final int _tmp = isFavorite ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, updatedAt);
        _argIndex = 3;
        for (long _item : ids) {
          _stmt.bindLong(_argIndex, _item);
          _argIndex++;
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object setPinnedBulk(final List<Long> ids, final boolean isPinned, final long updatedAt,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("UPDATE documents SET isPinned = ");
        _stringBuilder.append("?");
        _stringBuilder.append(", updatedAt = ");
        _stringBuilder.append("?");
        _stringBuilder.append(" WHERE id IN (");
        final int _inputSize = ids.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
        int _argIndex = 1;
        final int _tmp = isPinned ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, updatedAt);
        _argIndex = 3;
        for (long _item : ids) {
          _stmt.bindLong(_argIndex, _item);
          _argIndex++;
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteDocumentsByIds(final List<Long> ids,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("DELETE FROM documents WHERE id IN (");
        final int _inputSize = ids.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
        int _argIndex = 1;
        for (long _item : ids) {
          _stmt.bindLong(_argIndex, _item);
          _argIndex++;
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }

  private void __fetchRelationshippagesAscomSynthbyteScanmateDataPage(
      @NonNull final LongSparseArray<ArrayList<Page>> _map) {
    if (_map.isEmpty()) {
      return;
    }
    if (_map.size() > RoomDatabase.MAX_BIND_PARAMETER_CNT) {
      RelationUtil.recursiveFetchLongSparseArray(_map, true, (map) -> {
        __fetchRelationshippagesAscomSynthbyteScanmateDataPage(map);
        return Unit.INSTANCE;
      });
      return;
    }
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT `id`,`documentId`,`imagePath`,`pageOrder` FROM `pages` WHERE `documentId` IN (");
    final int _inputSize = _map.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _stmt = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (int i = 0; i < _map.size(); i++) {
      final long _item = _map.keyAt(i);
      _stmt.bindLong(_argIndex, _item);
      _argIndex++;
    }
    final Cursor _cursor = DBUtil.query(__db, _stmt, false, null);
    try {
      final int _itemKeyIndex = CursorUtil.getColumnIndex(_cursor, "documentId");
      if (_itemKeyIndex == -1) {
        return;
      }
      final int _cursorIndexOfId = 0;
      final int _cursorIndexOfDocumentId = 1;
      final int _cursorIndexOfImagePath = 2;
      final int _cursorIndexOfPageOrder = 3;
      while (_cursor.moveToNext()) {
        final long _tmpKey;
        _tmpKey = _cursor.getLong(_itemKeyIndex);
        final ArrayList<Page> _tmpRelation = _map.get(_tmpKey);
        if (_tmpRelation != null) {
          final Page _item_1;
          final long _tmpId;
          _tmpId = _cursor.getLong(_cursorIndexOfId);
          final long _tmpDocumentId;
          _tmpDocumentId = _cursor.getLong(_cursorIndexOfDocumentId);
          final String _tmpImagePath;
          _tmpImagePath = _cursor.getString(_cursorIndexOfImagePath);
          final int _tmpPageOrder;
          _tmpPageOrder = _cursor.getInt(_cursorIndexOfPageOrder);
          _item_1 = new Page(_tmpId,_tmpDocumentId,_tmpImagePath,_tmpPageOrder);
          _tmpRelation.add(_item_1);
        }
      }
    } finally {
      _cursor.close();
    }
  }
}
