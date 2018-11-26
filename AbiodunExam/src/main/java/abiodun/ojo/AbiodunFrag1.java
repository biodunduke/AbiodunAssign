/**
 * Abiodun Ojo
 * N01178447
 */
package abiodun.ojo;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AbiodunFrag1 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_abiodun, container, false);
        Button button = view.findViewById(R.id.abiButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText tv = view.findViewById(R.id.abiEditText);
                String validaText = tv.toString().trim();
                int myNum = 477;
                int inputValue = (int) Integer.parseInt(validaText);
                if (validaText.isEmpty()){
                    tv.setError("Cannot be empty");
                }
                if(inputValue == myNum){
                    Toast.makeText(getActivity(),"Equal",Toast.LENGTH_LONG).show();
                }
                else if(inputValue<myNum){
                    Toast.makeText(getActivity(),"Input is less than 477",Toast.LENGTH_LONG).show();
                }
                else if(inputValue>myNum){
                    Toast.makeText(getActivity(),"Input is greater than 477",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(getActivity(),"Invalid input",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
}
