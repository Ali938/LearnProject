package learn.coleo.com.learnproject.activities.main_page_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import learn.coleo.com.learnproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DeadLine extends Fragment {


    public DeadLine() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dead_line, container, false);
    }

}
