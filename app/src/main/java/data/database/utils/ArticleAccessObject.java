package data.database.utils;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import data.datamodels.Articles;


@Dao
public abstract class ArticleAccessObject {

    @Query("SELECT * FROM Articles")
    public abstract LiveData<List<Articles>> fetchAllData();

    @Query("SELECT * FROM Articles where dataQuery = :dataQuery")
    public abstract  List<Articles> searchByInterest(String dataQuery);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public  abstract  void createDataIfNotExists(List<Articles> ... articles);



}
