package learn.coleo.com.learnproject.server;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import learn.coleo.com.learnproject.activities.CodeActivity;
import learn.coleo.com.learnproject.activities.LoginActivity;
import learn.coleo.com.learnproject.activities.MainActivity;
import learn.coleo.com.learnproject.activities.SingleProjectDetails;
import learn.coleo.com.learnproject.activities.SingleTaskActivity;
import learn.coleo.com.learnproject.constants.Constants;
import learn.coleo.com.learnproject.data.Date;
import learn.coleo.com.learnproject.data.Project;
import learn.coleo.com.learnproject.data.Task;
import learn.coleo.com.learnproject.data.User;

public class ServerClass {

    private static final String TAG = "server class";
    private ServerClass() {
    }

    public static boolean hasConnection(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {
            return true;
        }

        return false;
    }

    private static void saveToken(Context context, JSONObject request) {
        try {
            Constants.setToken(context, request.getString("token"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void handleError(Context context, VolleyError error) {
        error.printStackTrace();
        if(error.networkResponse != null) {
            String s = new String(error.networkResponse.data);
            Log.i(TAG, "handleError: " + s);
        }
    }

    public static void checkPhone(final Context context, final String phone) {

        String url = Constants.URL_CHECK_PHONE;
        url += phone;
        url += "/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        ((LoginActivity) context).goCode(phone);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ((LoginActivity) context).enable();
                        handleError(context, error);
                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public static void sendCode(final Context context, final String phone, final String code) {

        String url = Constants.URL_SEND_CODE;
        url += phone;
        url += "/";
        url += code;
        url += "/";

        ObjectRequest jsonObjectRequest = new ObjectRequest
                (context, Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                saveToken(context, response);
                                ((CodeActivity) context).goMainPage();
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null) {
                            if (error.networkResponse != null) {
                                if (error.networkResponse.statusCode == 201) {
                                    String jsonString = new String(error.networkResponse.data);
                                    try {
                                        saveToken(context, new JSONObject(jsonString));
                                        //todo add sign up page
//                                        ((CodeActivity) context).goSignUp();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    ((CodeActivity) context).finish();
                                }
                            }
                        }
                        ((CodeActivity) context).wrongCode();
                        ServerClass.handleError(context, error);
                    }
                }
                );

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);

    }


    public static void getProjects(final Context context, final ArrayList<Project> list) {
        String url = Constants.PROJECTS_URL;

        ObjectRequest jsonObjectRequest = new ObjectRequest
                (context,Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                saveToken(context, response);

                                try {
                                    Constants.MINE = parseUser(response);
                                    JSONArray array = response.getJSONArray("projects");
                                    for(int i = 0;i<array.length();i++){
                                        list.add(projectParser(array.getJSONObject(i)));
                                    }
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }

                                ((MainActivity) context).projectChanged();
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ServerClass.handleError(context, error);
                    }
                }
                );

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    private static Project projectParser(JSONObject res){
        int id = 0, total = 0, done = 0;
        String name = null;
        try {
            id = res.getInt("id");
            name = res.getString("title");
            total = res.getInt("total_point");
            done = res.getInt("closed_point");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new Project(id, name, total, done);
    }

    public static void getProject(final Context context, int id) {
        String url = Constants.PROJECTS_URL + id + "/";

        ObjectRequest jsonObjectRequest = new ObjectRequest
                (context, Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                saveToken(context, response);

                                try {
                                    Project project = fullProjectParser(response.getJSONObject("project"));
                                    ((SingleProjectDetails) context).changed(project);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ServerClass.handleError(context, error);
                    }
                }
                );

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public static void getProjectTasks(final Context context,int id, final ArrayList<Task> tasks) {
        String url = Constants.PROJECTS_URL + id + "/tasks/";

        ObjectRequest jsonObjectRequest = new ObjectRequest
                (context, Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                saveToken(context, response);

                                parseTaskParent(response, tasks);

                                ((SingleProjectDetails) context).goTaskPage(tasks);
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ServerClass.handleError(context, error);
                    }
                }
                );

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public static void getTasks(final Context context, final ArrayList<Task> tasks) {
        String url = Constants.TASKS_URL;

        ObjectRequest jsonObjectRequest = new ObjectRequest
                (context, Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                saveToken(context, response);

                                parseTaskParent(response, tasks);

                                ((MainActivity) context).tasksChanged();
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ServerClass.handleError(context, error);
                    }
                }
                );

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public static void getTask(final Context context, final int id) {
        String url = Constants.TASKS_URL + id + "/";

        ObjectRequest jsonObjectRequest = new ObjectRequest
                (context, Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                saveToken(context, response);

                                Task t = null;
                                try {
                                    t = parseTask(response.getJSONObject("task"));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                ((SingleTaskActivity) context).update(t, null);
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ServerClass.handleError(context, error);
                    }
                }
                );

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    private static Project fullProjectParser(JSONObject res) {
        int id = 0, total = 0, done = 0;
        String name = null;

        int price = 0;
        User admin = null;
        try {
            id = res.getInt("id");
            name = res.getString("title");
            total = res.getInt("total_point");
            done = res.getInt("closed_point");
            price = res.getInt("price");
            admin = parseUser(res.getJSONObject("admin"));
            if (admin == null) {
                admin = new User("unknown user", 0);
            }
            Date started = dateParser(res, "date_started");
            Date create = dateParser(res, "date_created");
            Date finished = dateParser(res, "date_finished");
            ArrayList<Task> tasks = new ArrayList<>();
            JSONArray array = res.getJSONArray("tasks");
            for (int i = 0; i < array.length(); i++) {
                Task parsed = parseTask(array.getJSONObject(i));
                if (parsed != null) {
                    tasks.add(parsed);
                }
            }
            return new Project(id, name, admin, price, tasks
                    , create, started, finished, total, done);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void parseTaskParent(JSONObject object, ArrayList<Task> tasks) {
        if (tasks != null) {
            tasks.clear();
        } else {
            tasks = new ArrayList<>();
        }
        ArrayList<Task> child = new ArrayList<>();
        try {
            String key = "tasks";
            JSONArray tasksArray = object.getJSONArray(key);
            for (int i = 0; i < tasksArray.length(); i++) {
                JSONObject sampleTaskObject = tasksArray.getJSONObject(i);
                Task parsed = parseTask(sampleTaskObject);
                try {
                    JSONObject parent = sampleTaskObject.getJSONObject("parent");
                    int id = parent.getInt("id");
                    parsed.setParentId(id);
                    child.add(parsed);
                } catch (JSONException e) {
                    tasks.add(parsed);
                }
            }
            outer:
            for (Task temp : child) {
                for (Task parent : tasks) {
                    if (parent.getId() == temp.getParentId()) {
                        parent.addChild(temp);
                        continue outer;
                    }
                }
                Log.i(TAG, "parseTaskParent: notFoundParent");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private static Task parseTask(JSONObject object) {
        try {
            int id = object.getInt("id");
            String title = object.getString("title");
            String description = object.getString("description");
            User owner = parseUser(object.getJSONObject("user"));
            if (owner == null) {
                owner = new User("Unknown user");
            }
            int point = object.getInt("point");
            int volume = object.getInt("volume");
            Date deadLine = dateParser(object, "date_deadline");
            Date create = dateParser(object, "date_created");
            Date closed = dateParser(object, "date_closed");
            Date update = dateParser(object, "date_updated");
            return new Task(id, title, description, owner, point, volume,
                    deadLine, create, update, closed);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static User parseUser(JSONObject object) {
        try {
            String name = object.getString("first_name");
            name += " ";
            name += object.getString("last_name");
            return new User(name);
        } catch (JSONException e) {
        }
        return null;
    }

    private static Date dateParser(JSONObject object, String key) {
        try {
            JSONObject date = object.getJSONObject(key);
            return new Date(date.getInt("day"), date.getInt("month"), date.getInt("year"),
                    date.getInt("hour"), date.getInt("minute"), date.getInt("second"),
                    date.getString("day_name"), date.getString("month_name"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}