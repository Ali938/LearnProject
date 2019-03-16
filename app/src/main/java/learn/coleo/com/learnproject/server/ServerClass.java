package learn.coleo.com.learnproject.server;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import learn.coleo.com.learnproject.Constants;
import learn.coleo.com.learnproject.LoginActivity;
import learn.coleo.com.learnproject.MainActivity;
import learn.coleo.com.learnproject.SingleProjectDetails;
import learn.coleo.com.learnproject.data.Project;

public class ServerClass {

    private static final String TAG = "server class";
    private ServerClass() {
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

    public static void login(final Context context, String username, String password) {
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
                                ((LoginActivity) context).nextPage();
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
        return new Project(name,id);

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