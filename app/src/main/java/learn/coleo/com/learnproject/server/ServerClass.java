package learn.coleo.com.learnproject.server;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import learn.coleo.com.learnproject.constants.Constants;
import learn.coleo.com.learnproject.LoginActivity;
import learn.coleo.com.learnproject.MainActivity;
import learn.coleo.com.learnproject.SingleProjectDetails;
import learn.coleo.com.learnproject.data.Project;
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

    private static void handleError(Context context, VolleyError error, int methodId) {
        error.printStackTrace();
        if(error.networkResponse != null) {
            String s = new String(error.networkResponse.data);
            Log.i(TAG, "handleError: " + s);

        }
    }

    public static void login(final Context context, final String username, String password) {
        String url = Constants.LOGIN_URL;

        JSONObject temp = new JSONObject();
        try {
            JSONObject developer = new JSONObject();
            developer.put("username", username);
            developer.put("password", password);
            temp.put("developer", developer);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ObjectRequest jsonObjectRequest = new ObjectRequest
                (context,Request.Method.POST, url, temp,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                saveToken(context, response);
                                ((LoginActivity) context).nextPage(username);
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ServerClass.handleError(context, error, 0);
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
                                    JSONArray array = response.getJSONArray("projects");
                                    for(int i = 0;i<array.length();i++){
                                        list.add(projectParser(array.getJSONObject(i)));
                                    }
                                }catch (JSONException e){
                                    e.printStackTrace();
                                }

                                ((MainActivity) context).changed();
                            }
                        }
                        , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ServerClass.handleError(context, error, 0);
                    }
                }
                );

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    private static Project projectParser(JSONObject res){
        int id = 0;
        String name = null;
        try {
            id = res.getInt("id");
            name = res.getString("title");
        }catch (JSONException e){
            e.printStackTrace();
        }
        if (name == null){
            return null;
        }
        //todo clean
        return new Project(id,name,new User("alireza",0),0);

    }

    public static void getProject(Context context, int id) {
//        String url = Constants.URL_GET_ADDRESS;
//        url += Constants.getToken(context);
//        url += "/";
//        final ArrayList<CitizenAddress> addresses = new ArrayList<>();
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        ArrayList<CitizenAddress> addresses1 = parseAddress(response);
//                        addresses.addAll(addresses1);
//                        if (view == null) {
//                            ((AddressActivity) context).changed();
//                        } else {
//                            ((TabActivity) context).tabLayout.continueMain(addresses);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        ServerClass.handleError(context, error);
//                    }
//                });
//
//        // Access the RequestQueue through your singleton class.
//        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
//        return addresses;
        ((SingleProjectDetails) context).changed();
    }


}