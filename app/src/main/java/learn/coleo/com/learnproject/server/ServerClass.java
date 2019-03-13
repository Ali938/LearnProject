package learn.coleo.com.learnproject.server;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import learn.coleo.com.learnproject.Constants;
import learn.coleo.com.learnproject.LoginActivity;
import learn.coleo.com.learnproject.MainActivity;
import learn.coleo.com.learnproject.SingleProjectDetails;

public class ServerClass {

    private static void handleError(Context context, VolleyError error, int methodId) {
        error.printStackTrace();
    }

    private static void putTokenInRequest(Context context, JsonObjectRequest jsonObjectRequest) {
        String token = Constants.getToken(context);
        try {
            jsonObjectRequest.getHeaders().put("token", token);
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
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

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, temp,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
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

        putTokenInRequest(context,jsonObjectRequest);

        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);

    }

    public static void getProjects(Context context) {
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
        ((MainActivity) context).changed();
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