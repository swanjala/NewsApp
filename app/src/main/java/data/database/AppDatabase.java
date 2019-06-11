package data.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.support.annotation.NonNull;

import data.database.accessobjects.ArticleAccessObject;
import data.database.accessobjects.SourcesAccessObject;
import data.database.utils.NewsAppTypeConverter;
import data.datamodels.Articles;
import data.datamodels.Sources;


@Database(entities = {Articles.class, Sources.class}, version = 17)
@TypeConverters({NewsAppTypeConverter.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract ArticleAccessObject articleAccessObject();
    public abstract SourcesAccessObject sourcesAccessObject();
    private static volatile AppDatabase INSTANCE;

    public static  Context dbContext;
    public static AppDatabase getDatabase(final Context context){

        dbContext= context;

        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .addCallback(newsSourcesCallBack)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }

            }
        }

        return INSTANCE;
    }


    private static RoomDatabase.Callback newsSourcesCallBack =
            new RoomDatabase.Callback(){
        @Override
                public void onOpen (@NonNull SupportSQLiteDatabase database){
            super.onOpen(database);
            new DatabaseDataManager(dbContext, INSTANCE).execute();
        }
    };

    public static AppDatabase runUpdateTask(final Context context){
        dbContext= context;

        if (INSTANCE == null){
            synchronized (AppDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .addCallback(newsSourcesCallBack)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }

            }
        }

        return INSTANCE;
    }

    public static RoomDatabase.Callback
}
