package com.xtrachat.view.contact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.xtrachat.R;
import com.xtrachat.adapter.ContactsAdapter;
import com.xtrachat.databinding.ActivityContactsBinding;
import com.xtrachat.model.users.Users;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    private static final String TAG = "ContactsActivity" ;
    private ActivityContactsBinding binding;
    private List<Users> list = new ArrayList<>();
    private ContactsAdapter adapter;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contacts);



        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firestore = FirebaseFirestore.getInstance();


        if(firebaseUser != null) {
           getContactList();
      }

    }

    private void getContactList() {

        firestore.collection("Users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot snapshots : queryDocumentSnapshots) {
                    String userID = snapshots.getString("userID");
                    String userName = snapshots.getString("userName");
                    String imageUrl = snapshots.getString("imageProfile");
                    String desc = snapshots.getString("bro");

                    Users user = new Users();
                    user.setUserID(userID);
                    user.setBro(desc);
                    user.setUserName(userName);
                    user.setImageProfile(imageUrl);
                    if(userID!= null && !userID.equals(firebaseUser.getUid())) {
                        list.add(user);
                    }
                }
                adapter = new ContactsAdapter(list, ContactsActivity.this);
                binding.recyclerView.setAdapter(adapter);

            }
        });
    }
}