package com.example.pojofirebase.network;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class utils {

    public static Task<Void> removeuser(String userID){
        Task<Void> task = FirebaseDatabase.getInstance().getReference("users").child(userID).setValue(null);
        return task;

    }
}
