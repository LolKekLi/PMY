package com.example.logpass;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

public class ProfilFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_profil, container, false);

        Button viewById = inflate.findViewById(R.id.refactorButton);
        Button back = inflate.findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Fragment> fragments = getParentFragmentManager().getFragments();

                getParentFragmentManager().beginTransaction().
                        hide(getParentFragmentManager().getFragments().get(1))
                        .commit();
                getParentFragmentManager().beginTransaction().
                        show(getParentFragmentManager().getFragments().get(0))
                        .commit();
            }
        });

        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (savedInstanceState == null) {

                    List<Fragment> fragments = getParentFragmentManager().getFragments();

                    getParentFragmentManager().beginTransaction().hide(getParentFragmentManager().getFragments().get(1)).commit();

                    if (fragments.size()<3)
                    {
                        getParentFragmentManager().beginTransaction()
                                .add(R.id.fragment_container_view, RefactorProfilFrag.class, null)
                                .commit();
                    }
                    else
                    {
                        getParentFragmentManager().beginTransaction().
                                show(getParentFragmentManager().getFragments().get(2)).commit();
                    }

                }
            }
        });


        return inflate;
    }
}