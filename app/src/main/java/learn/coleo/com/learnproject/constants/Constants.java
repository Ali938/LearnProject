package learn.coleo.com.learnproject.constants;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.view.Display;

import java.util.ArrayList;

import learn.coleo.com.learnproject.data.Task;
import learn.coleo.com.learnproject.data.User;

public class Constants {

    private Constants(){}
    public static User MINE = null;
    private final static String TOKEN_STORAGE = "someWhereInDarkness";
    private final static String TOKEN_DATA = "someWhereInDarkness12";
    public final static String NO_TOKEN = "nothingInTheBag";

    public static final String SINGLE_PROJECT_DATA= "datasada";
    public static final String TASK_ID_DATA = "aslcje";
    public static final String SINGLE_PROJECT_ID= "idasd";
    public static final String SINGLE_PROJECT_NAME= "namesasd";
    public static final String DETAIL_SECTION = "detdsads";

    public static int SUBMIT_CODE = 129;
    public static int RESULTE_OK = 100;

    //url
    private static final String BASE_URL = "http://abbas.coleo.me/api/v1/";
    public static final String PROJECTS_URL = BASE_URL + "projects/";
    public static final String TASKS_URL = BASE_URL + "tasks/";
    public static final String URL_CHECK_PHONE = BASE_URL + "auth/check_phone/";
    public static final String URL_SEND_CODE = BASE_URL + "auth/check_code/";

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

    public static int getHeightOfScreen(Activity context){
        Display display = context.getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display. getSize(size);
        return size. y;
    }

    public static int getWidthOfScreen(Activity context){
        Display display = context.getWindowManager(). getDefaultDisplay();
        Point size = new Point();
        display. getSize(size);
        return size. x;
    }

    public static float pxToDp(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float dpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public static ArrayList<Task> getUserTask(ArrayList<Task> tasks, User user) {
        ArrayList<Task> mine = new ArrayList<>();
        for (Task t : tasks) {
            if (!t.getChildren().isEmpty()) {
                if (amIDev(t.getChildren(), user))
                    mine.add(t);
            } else {
                if (t.getOwner().equals(user)) {
                    mine.add(t);
                }
            }
        }
        return mine;
    }

    private static boolean amIDev(ArrayList<Task> tasks, User user) {
        for (Task t : tasks) {
            if (t.getChildren().isEmpty()) {
                if (t.getOwner().equals(user))
                    return true;
            } else {
                return amIDev(t.getChildren(), user);
            }
        }
        return false;
    }

}
