package abiodun.ojo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import ru.alexbykov.nopermission.PermissionHelper;


public class AbiDown extends Fragment implements AbHome.OnFragmentInteractionListener {
    TextView tv;
    URL url, url2, url3;
    int k = 0;
    DownloadFilesTask dw;
    String[] fileName = {""};
    ImageView mImageView;
    private Button downBut;
    String imageName;
    String[] path;
    String PATH;
    Bitmap myBitmap;
    File folder;
    File temp;
    PermissionHelper permissionHelper;
    private OnFragmentInteractionListener mListener;
    private static final int PERMISSION_REQUEST_CODE = 5 ;

    public AbiDown() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.abi_down, container, false);
        tv = view.findViewById(R.id.abiodunProgressUpdate);

        mImageView = view.findViewById(R.id.abiodunImage1);
        downBut = (Button) view.findViewById(R.id.abiodunDownloadButton);
        tv.setVisibility(mImageView.GONE);
        //  PermissionsManager permission = new PermissionsManager();
        //permission.checkPermissions();
  //      permissionHelper = new PermissionHelper(this);
        try {
            url = new URL("https://cdn.pixabay.com/photo/2017/01/06/23/21/soap-bubble-1959327_640.jpg");

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {

            path = url.getPath().split("/");
            // we are interested only in the last index
            imageName = path[path.length - 1];

            PATH = getContext().getFilesDir() + "/Download/"; //getFilesDir() will return the files path of the package
            folder = new File(PATH);
            temp = new File(folder + "/" + imageName);
            if (temp.exists()) {
                //  fileName[0] = temp.toString();//folder + "/" + imageName;
               // downBut.setEnabled(false);
                tv.setVisibility(mImageView.VISIBLE);
                tv.setText(getString(R.string.fileExists)); //Show that the file exists
                myBitmap = BitmapFactory.decodeFile(temp.toString()); //0 for Apple, 2 for Zelda
                mImageView.setImageBitmap(myBitmap);
                downBut.setEnabled(false); //Disable the button
            } else
                folder.mkdirs();
            fileName[0] = folder + "/" + imageName;
        } catch (Exception e) {

        }
        downBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dw = new DownloadFilesTask();
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission
                            .READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.INTERNET,Manifest.permission.CAMERA,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.READ_SMS
                    },PERMISSION_REQUEST_CODE);
                } else {
                    dw.execute(url, url, url);
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
            //      throw new RuntimeException(context.toString() + getString(R.string.mustImpFragment));

        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //    mListener = null;
    }

    @Override
    public void onFragmentInteraction(String name, String desc) {

    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class DownloadFilesTask extends AsyncTask<URL, Integer, Long> {

        @Override
        protected Long doInBackground(URL... urls) {
            int count = urls.length;

            // set the size of the array that holds the file names
            fileName = new String[count];

            long totalSize = 0;
            for (int i = 0; i < count; i++) {
                totalSize += DownloadFile(urls[i], i);
                publishProgress((int) ((i / (float) count) * 100));
                // Escape early if cancel() is called
                if (isCancelled()) break;
            }
            return totalSize;
        }

        private long DownloadFile(URL url, int i) {
            // TODO Auto-generated method stub
            int total = 0;
            try {


                URLConnection conn = url.openConnection();
                if (!(conn instanceof HttpURLConnection))
                    throw new IOException(getString(R.string.notHTTP));


                HttpURLConnection httpConn = (HttpURLConnection) conn;

                //httpConn.setAllowUserInteraction(false);
                //httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();

                int response = httpConn.getResponseCode();
                if (response == HttpURLConnection.HTTP_OK) {
                    InputStream input = httpConn.getInputStream();

                    // download the file
                    // each portion will stored into an index

                    fileName[i] = folder + "/" + imageName;
                    OutputStream output = new FileOutputStream(fileName[i]);

                    byte data[] = new byte[1024];

                    int count;
                    while ((count = input.read(data)) != -1) {
                        total += count;
                        output.write(data, 0, count);
                    }
                    output.flush();
                    output.close();
                    input.close();

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            return total;
        }


        protected void onPostExecute(Long result) {
            Toast.makeText(getContext(), getString(R.string.downloaded) + result + getString(R.string.bytesText), Toast.LENGTH_LONG).show();
            myBitmap = BitmapFactory.decodeFile(fileName[0]); //0 for Apple, 2 for Zelda
            mImageView.setImageBitmap(myBitmap);
            downBut.setEnabled(false); //disable the button after download
            //TO fade out the text after download complete
        /*    final Animation fadeOut = new AlphaAnimation(1.0f, 0.0f);
            fadeOut.setDuration(3000);
           tv.startAnimation(fadeOut);
            tv.setVisibility(mImageView.GONE); //Remove the percent complete after fadeout
          */
        }


        protected void onProgressUpdate(Integer... values) {
            Log.d(getString(R.string.asyncTask), getString(R.string.progressUpdate) + values[0]);
            int i = values.length;
            tv.setVisibility(mImageView.VISIBLE);

            for (int ii = 0; ii < i; ii++) {
                k = k + values[ii];
            }

            tv.setText(String.valueOf(k + 1) + getString(R.string.completion_percentage));//Show the % complete
        }


        protected void onCancelled() {
            // stop the progress
            tv.setText(getString(R.string.downloadStopped));
            Toast.makeText(getContext(), getString(R.string.cancelCalled), Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getActivity(),getString(R.string.request_granted),Toast.LENGTH_LONG).show();
                 //   dw = new DownloadFilesTask();
                    dw.execute(url, url, url);
                }
                else
                    Toast.makeText(getActivity(),getString(R.string.request_declined),Toast.LENGTH_LONG).show();
                break;
        }
    }

}
