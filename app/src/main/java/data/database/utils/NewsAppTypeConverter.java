package data.database.utils;

import android.annotation.SuppressLint;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.util.StringUtil;

import java.util.List;

@SuppressLint("RestrictedApi")
public class NewsAppTypeConverter {

    @TypeConverter
    public static String intListToString(List<Integer> ints) {
        return StringUtil.joinIntoString(ints);
    }
}
