package com.bkpirates.bookstore;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Admin on 1/1/2017.
 */
public class LoginDBFragment extends Fragment {
    Button button;
    EditText editNameDB,editServer,editUserName,editPassWord;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login_db,container,false);
        button=(Button)view.findViewById(R.id.btn_login_db);
                editNameDB=(EditText)view.findViewById(R.id.text_db_name);
                editServer=(EditText)view.findViewById(R.id.text_db_server);
                editUserName=(EditText)view.findViewById(R.id.text_user_name);
                editPassWord=(EditText)view.findViewById(R.id.text_user_pw);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(getActivity(),MainHomeActivity.class);
                getActivity().finish();
                startActivity(intent);
            }
        });
        return view;
    }
}
