package com.jdirectionsapp123456.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import com.jdirectionsapp123456.R;
import com.jdirectionsapp123456.model.profile.ProfileDataModel;

public class ProfileAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<ProfileDataModel> profileDataModels = new ArrayList<>();

    public ProfileAdapter(ArrayList<ProfileDataModel> profileDataModels, Context context) {
        this.mContext = context;
        this.profileDataModels = profileDataModels;
    }

    @Override
    public int getCount() {
        Log.e("TAG", "getCount: " + profileDataModels.size());
        return profileDataModels.size();
    }

    @Override
    public Object getItem(int position) {
        Log.e("TAG", "getView: " + profileDataModels.get(position));
        return profileDataModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ImageView imageView = (ImageView) layoutInflater.inflate(R.layout.imageview, null);

        Glide.with(mContext)
                .applyDefaultRequestOptions(new RequestOptions().override(200, 200))
                .load(profileDataModels.get(position).getCloseupimage())
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher)
                .into(imageView);
        Log.e("TAG", "getView: " + profileDataModels.get(position));

       /* TypedArray a = mContext.obtainStyledAttributes(R.styleable.Gallery1);
        int mGalleryItemBackground = a.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 0);
        imageView.setBackgroundResource(mGalleryItemBackground);
        a.recycle();*/
        return imageView;
    }
}
