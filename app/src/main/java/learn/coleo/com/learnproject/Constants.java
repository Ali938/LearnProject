package learn.coleo.com.learnproject;

import android.content.Context;
import android.content.SharedPreferences;

public class Constants {

    private Constants(){}

    private final static String TOKEN_STORAGE = "someWhereInDarkness";
    private final static String TOKEN_DATA = "someWhereInDarkness12";
    private final static String NO_TOKEN = "nothingInTheBag";

    public static final String SINGLE_PROJECT_DATA= "datasada";
    public static final String SINGLE_PROJECT_ID= "idasd";
    public static final String SINGLE_PROJECT_NAME= "namesasd";
    public static final String DETAIL_SECTION = "detdsads";

    private static final String BASE_URL = "http://abbas.coleo.me/api/";
    public static final String LOGIN_URL = BASE_URL + "v1/auth/sign_in/";

    public static void setToken(Context context, String token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TOKEN_STORAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_DATA, token);
        editor.apply();
        editor.commit();
    }

    public static String getToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TOKEN_STORAGE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TOKEN_DATA, NO_TOKEN);
    }


}
