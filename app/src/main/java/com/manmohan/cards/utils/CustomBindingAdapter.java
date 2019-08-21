package com.manmohan.cards.utils;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.manmohan.cards.R;
import com.squareup.picasso.Picasso;

public class CustomBindingAdapter {
    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView view, String url) {
        Picasso.with(view.getContext())
            .load(url)
            .placeholder(R.drawable.user_icon)
            .into(view);
    }
}