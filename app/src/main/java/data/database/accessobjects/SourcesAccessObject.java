package data.database.accessobjects;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import data.datamodels.Articles;
import data.datamodels.Source;

@Dao
public abstract class SourcesAccessObject {

    @Query("SELECT * FROM source")
    public abstract LiveData<List<Source>> fetchAllData();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract  void createSourceDataIfNotExists(Source... sourceData);

    @Query("SELECT country FROM source ")
    public abstract  void fetchCountryLists();


}
