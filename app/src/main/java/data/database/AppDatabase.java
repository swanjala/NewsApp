package data.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import data.database.utils.ArticleAccessObject;
import data.database.utils.ArticleDatabase;
import data.datamodels.Articles;


@Database(entities = {Articles.class}, version = 6)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ArticleAccessObject articleAccessObject();
    private static volatile AppDatabase INSTANCE;


    public static AppDatabase getDatabase(final Context context){

        if(INSTANCE == null) {

            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class,"app_database")
                            .addCallback(newsRoomDatabaseCallback)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }

        return INSTANCE;

    }

    private static RoomDatabase.Callback newsRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase database){
                    super.onOpen(database);
                    new PopulateDbAsync(INSTANCE).execute();
                }

            };

}
