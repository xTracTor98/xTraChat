package com.xtrachat.menu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xtrachat.R;
import com.xtrachat.adapter.ChatListAdapter;
import com.xtrachat.model.ChatList;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    public ChatFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        List<ChatList> list = new ArrayList<>();
        RecyclerView recyclerView;

        recyclerView = view.findViewById((R.id.recyclerView));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        recyclerView.setAdapter((new ChatListAdapter(list, getContext())));
        return view;
    }
}