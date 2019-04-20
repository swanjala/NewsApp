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

    @Query("SELECT * FROM articles where author LIKE :countryQuery")
    public abstract LiveData<List<Articles>> fetchAllCountryData(String countryQuery);

    @Query("SELECT * FROM  articles where url LIKE :domainQuery")
    public abstract  LiveData<List<Articles>> fetchDataByDomain(String domainQuery);

    @Query("SELECT * FROM articles where toRead = 1")
    public abstract LiveData<List<Articles>> fetchDataByToRead();

    @Query("SELECT * FROM articles where favorite = 1")
    public abstract LiveData<List<Articles>> fetchDataByFavorite();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public  abstract void createDataIfNotExists(Articles ... articleData);

    @Query("UPDATE articles SET favorite=:favValue WHERE title LIKE :titleQuery")
    public abstract  void setNewsItemToFavorite(boolean favValue, String titleQuery);

    @Query("UPDATE articles SET toRead=:readValue where title LIKE :titleQuery")
    public abstract void setNewsItemToRead(boolean readValue, String titleQuery);

}
