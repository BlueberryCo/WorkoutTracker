package blueberryco.entities.locale;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;

import java.util.Locale;

/**
 * Created by boiko on 14-Mar-16.
 */
public class FitInApplication extends Application {

    public static final String LANGUAGE_KEY = "Language";
    private static final String BG_LANGUAGE = "bg";
    private static final String EN_LANGUAGE = "en";

    @Override
    public void onCreate() {
        updateLanguage(this);
        super.onCreate();
    }

    public static void updateLanguage(Context context){
        updateLanguage(context, getLanguage(context));
    }

    private static void updateLanguage(Context context, String language){
        Configuration configuration = new Configuration();
        configuration.locale = new Locale(language);
        context.getResources().updateConfiguration(configuration, null);
    }

    public static void updateLanguageToBg(Context context){
        setLanguage(context, BG_LANGUAGE);
    }

    public static void updateLanguageToEn(Context context){
        setLanguage(context, EN_LANGUAGE);
    }

    private static String getLanguage(Context context){
        SharedPreferences preffs = PreferenceManager.getDefaultSharedPreferences(context);
        return preffs.getString(LANGUAGE_KEY, BG_LANGUAGE);
    }

    private static void setLanguage(Context context, String language){
        SharedPreferences preffs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preffs.edit();
        editor.putString(LANGUAGE_KEY, language);
        editor.commit();
    }
}
