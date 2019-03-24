package data.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import data.database.utils.ArticleAccessObject;
import data.database.utils.ArticleDatabase;
import data.database.utils.SourcesAccessObject;
import data.datamodels.Articles;
import data.datamodels.Source;


@Database(entities = {Articles.class, Source.class}, version = 9)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ArticleAccessObject articleAccessObject();
    public abstract SourcesAccessObject sourcesAccessObject();
    private static volatile AppDatabase INSTANCE;

    public static  Context dbContext;



    public static AppDatabase getDatabase(final Context context){

        dbContext= context;

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
                    new PopulateDbAsync(dbContext,INSTANCE).execute();
                }

            };

}
