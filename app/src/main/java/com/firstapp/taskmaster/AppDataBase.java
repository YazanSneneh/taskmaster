package com.firstapp.taskmaster;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TaskModel.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {
      public abstract DataAccessObject tasksDao();
}
