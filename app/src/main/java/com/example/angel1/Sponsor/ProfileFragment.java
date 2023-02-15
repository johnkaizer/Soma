package com.example.angel1.Sponsor;

import static com.example.angel1.Adapters.DBmain.TABLENAME;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.angel1.Adapters.ApprovedAdapter;
import com.example.angel1.Adapters.DBmain;
import com.example.angel1.Model.ApprovedModel;
import com.example.angel1.R;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    int id = 0;
    RecyclerView applicationRV;
    ApprovedAdapter approvedAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=  inflater.inflate(R.layout.fragment_profile, container, false);
        dBmain = new DBmain(getContext());
        applicationRV= view.findViewById(R.id.approved);
        applicationRV.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        displayData();


        return view;
    }

    private void displayData() {
        sqLiteDatabase = dBmain.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLENAME+" WHERE  stdParents = 'None works' AND stdOrphan= 'Yes' AND stdDisabilities = 'Yes'", null);
        ArrayList<ApprovedModel> approvedModel = new ArrayList<>();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String stdName =cursor.getString(1);
            String appTitle=cursor.getString(2);
            String stdEmail =cursor.getString(3);
            String stdSchool=cursor.getString(4);
            String stdFee=cursor.getString(5);
            String stdParents=cursor.getString(6);
            String stdOrphan=cursor.getString(7);
            String stdDisabilities=cursor.getString(8);
            approvedModel.add(new ApprovedModel(id,stdName,appTitle,stdEmail,stdSchool,stdFee,stdParents,stdOrphan,stdDisabilities));

        }
        cursor.close();
        approvedAdapter = new ApprovedAdapter(getContext(),R.layout.application_admin_item,approvedModel,sqLiteDatabase);
        applicationRV.setAdapter(approvedAdapter);
    }

}