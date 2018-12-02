package abiodun.ojo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class OjSrv extends Fragment implements AbHome.OnFragmentInteractionListener/* ,AbiDown.OnFragmentInteractionListener*/ {

    private OnFragmentInteractionListener mListener;
    Button btnGet;
    EditText zipText;
    TextView webContent;
    String textStr = "";
    String textSource;
    String zipCode;
    JSONObject jsonObj, coord, weather;
    String lon, lat, humidity, name, jsonResult;

    public OjSrv() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.oj_srv, container, false);
        btnGet = view.findViewById(R.id.abiodunWebCall);
        zipText = view.findViewById(R.id.abiodunEditZip);
        webContent = view.findViewById(R.id.abiodunWebContent);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //Zip Code Validation
                    zipCode = zipText.getText().toString();
                    if (zipCode.isEmpty()) {
                        zipText.setError(getString(R.string.enterZip));
                        zipText.requestFocus();
                    } else if (zipCode.length() < 5) {
                        zipText.setError(getString(R.string.zipLenght));
                        zipText.requestFocus();
                    } else
                        try {
                            //Register to get API key
                            textSource = "https://api.openweathermap.org/data/2.5/weather?zip=" + zipCode +
                                    ",us&appid=5a0684625d9b4a70efbb4616cabdbcf0";
                            webContent.setText(getString(R.string.gettingWebStuff));

                            // To DO
                            new Thread() { //This moves the network call to another thread (Process)
                                @Override
                                public void run() {

                                    URL url = null;
                                    HttpURLConnection urlConnection;

                                    try {
                                        url = new URL(textSource);
                                        urlConnection = (HttpURLConnection) url.openConnection();

                                        int responseCode = urlConnection.getResponseCode();
                                        if (responseCode == HttpURLConnection.HTTP_OK) { //Check if server can be reached
                                            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(url.openStream()));
                                            String StringBuffer;

                                            while ((StringBuffer = bufferReader.readLine()) != null) {
                                                textStr += StringBuffer; //Keep concatenating the buffer line by line
                                            }
                                            bufferReader.close(); //Close the stream
                                        }


                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                //TODO: Parse JSon

                                                if (!textStr.isEmpty()) {
                                                    try {
                                                        /*http://json.parser.online.fr/ to parse*/
                                                        jsonObj = new JSONObject(textStr);
                                                        name = jsonObj.getString("name");
                                                        coord = jsonObj.getJSONObject("coord");
                                                        weather = jsonObj.getJSONObject("main");
                                                        humidity = weather.getString("humidity");
                                                        lon = coord.getString("lon");
                                                        lat = coord.getString("lat");
                                                        jsonResult = "\nName: " + name + "\nZip Code: " + zipCode
                                                                + "\nLongitude: " + lon
                                                                + "\nLatitude: " + lat + "\nHumidity: " + humidity
                                                                + ".";
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                    webContent.setText(getString(R.string.finished) + jsonResult);
                                                    Toast.makeText(getContext(), getString(R.string.dataRetireved), Toast.LENGTH_LONG).show();
                                                } else {
                                                    webContent.setText(getString(R.string.finished) + getString(R.string.zipNotFound));
                                                    zipText.setError(getString(R.string.zipNotFound));
                                                    zipText.requestFocus();
                                                }
                                                textStr = "";//Clear the string after displaying
                                                try {

                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        });
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }.start();
                        } catch (Exception e) {

                        }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO:
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            //     throw new RuntimeException(context.toString() + getString(R.string.mustImpFragment));
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onFragmentInteraction(String name, String desc) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
