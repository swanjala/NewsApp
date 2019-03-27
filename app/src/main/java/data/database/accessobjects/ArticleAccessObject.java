package data.database.accessobjects;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import data.datamodels.Articles;


@Dao
public abstract class ArticleAccessObject {

    @Query("SELECT * FROM articles")
    public abstract LiveData<List<Articles>> fetchAllData();

    @Query("SELECT * FROM articles where title LIKE :dataQuery")
    public abstract LiveData<List<Articles>> fetchAllData(String dataQuery);

    @Query("SELECT * FROM  articles where url LIKE :domainQuery")
    public abstract  LiveData<List<Articles>> fetchDataByDomain(String domainQuery);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public  abstract void createDataIfNotExists(Articles ... articleData);

}
