package com.group4.eventhandler;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by Parsa on 09/08/2015.
 */
public class FeedAdapter extends BaseAdapter {

    Context context;
    ArrayList arrayList;
    private static LayoutInflater inflater = null;
    public Resources res;
    Event event = null;
    static int itemId;
    private Activity activity;
    Uri imageUri;

    public FeedAdapter(Activity a, ArrayList arrayList, Resources res) {
        // TODO Auto-generated constructor stub
        activity = a;
        context = a.getApplicationContext();
        this.arrayList = arrayList;
        this.res = res;
        inflater = (LayoutInflater) activity
                .getSystemService(activity.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        if (arrayList.size() <= 0)
            return 1;
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * ****** Create a holder Class to contain inflated xml file elements ********
     */
    public static class ViewHolder {

        public TextView tv_headline;
        public TextView tv_desc;
        public TextView tv_time;
        public ImageView iv_image;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View vi = convertView;
        ViewHolder holder;
        if (vi == null) {
            vi = inflater.inflate(R.layout.feed_item, null);
            holder = new ViewHolder();
            holder.tv_headline = (TextView) vi.findViewById(R.id.tv_headline_feed);
            holder.tv_desc = (TextView) vi.findViewById(R.id.tv_desc_feed);
            holder.tv_time = (TextView) vi.findViewById(R.id.tv_time_feed);
            holder.iv_image = (ImageView) vi.findViewById(R.id.iv_photo_feed);
            /************  Set holder with LayoutInflater ************/
            vi.setTag(holder);

        } else
            holder = (ViewHolder) vi.getTag();

        if (arrayList.size() <= 0) {
            holder.tv_headline.setText("No Data");

        } else {
            /***** Get each Model object from Arraylist ********/
            event = null;
            event = (Event) arrayList.get(position);

            /************  Set Model values in Holder elements ***********/

            holder.tv_headline.setText(event.getHeadline());
            holder.tv_desc.setText(event.getDescription());
            holder.tv_time.setText(event.getTime());


            imageUri = Uri.parse(event.getImageUrl());

            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(imageUri));
                holder.iv_image.setImageBitmap(bitmap);
                holder.iv_image.setScaleType(ImageView.ScaleType.FIT_XY);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //******** Set Item Click Listner for LayoutInflater for each row *******//*
            vi.setOnClickListener(new OnItemClickListener(position));

        }
        return vi;
    }

    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position) {
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {


            FeedActivity sct = (FeedActivity) activity;
            sct.onItemClick(mPosition);
        }
    }
}
