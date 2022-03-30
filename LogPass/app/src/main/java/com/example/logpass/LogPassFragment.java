package com.example.logpass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import java.util.List;


public class LogPassFragment extends Fragment {

    String correctPass = "12345";
    String correctLogin = "Login";

     public LogPassFragment()
    {
        super(R.layout.fragment_test);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        Log.e("F", "onCreateView: ");

        View inflate = inflater.inflate(R.layout.fragment_test, container, false);

        Button refactorButton = inflate.findViewById(R.id.button);
        EditText login = inflate.findViewById(R.id.Login);
        EditText pass = inflate.findViewById(R.id.Password);

        refactorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = String.valueOf(login.getText());
                String text1 = String.valueOf(pass.getText());
                if (!text.equalsIgnoreCase(correctLogin) || !text1.equalsIgnoreCase(correctPass))
                {
                    Toast toast = Toast.makeText(getActivity(), "Логин или пароль введены неправильно", Toast.LENGTH_LONG);
                    toast.show();

                    return;
                }

                if (savedInstanceState == null)
                {


                    getParentFragmentManager().beginTransaction()
                            .hide(getParentFragmentManager().getFragments().get(0))
                            .commit();

                    List<Fragment> fragment = getParentFragmentManager().getFragments();
                    if (fragment.size() <= 1) {
                        getParentFragmentManager().beginTransaction()
                                .add(R.id.fragment_container_view, ProfilFragment.class, null)
                                .commit();
                    }
                    else
                    {
                        getParentFragmentManager().beginTransaction()
                                .show(fragment.get(1))
                                .commit();
                    }

                }
            }
        });


        return inflate;
    }
}