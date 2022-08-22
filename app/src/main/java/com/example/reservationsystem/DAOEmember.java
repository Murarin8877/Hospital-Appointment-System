package com.example.reservationsystem;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.DatabaseMetaData;

public class DAOEmember {
    private DatabaseReference databaseReference;
    public DAOEmember()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(UserHelperClass.class.getSimpleName());

    }
    public Task<Void> add(member mem)
    {
        return databaseReference.push().setValue(mem);

    }
}
