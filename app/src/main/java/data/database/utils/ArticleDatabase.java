package data.database.utils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import data.datamodels.Articles;
import data.datamodels.Source;



public abstract class ArticleDatabase  {

    public abstract ArticleAccessObject articleAccessObject();
}
