package data.database.accessobjects;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import data.datamodels.Sources;

@Dao
public abstract class SourcesAccessObject {

    @Query("SELECT * FROM Sources")
    public abstract LiveData<List<Sources>> fetchAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract  void createSourceDataIfNotExists(Sources... sourceData);

    @Query("SELECT DISTINCT(country) FROM Sources ")
    public abstract  LiveData<List<String>> fetchCountryLists();

    @Query("SELECT DISTINCT(category) FROM Sources")
    public abstract LiveData<List<String>> fetchCategoryList();

    @Query("SELECT * FROM Sources WHERE category LIKE :categoryQuery")
    public abstract LiveData<List<Sources>> fetchNewsNamesByCategory(String categoryQuery);


}
