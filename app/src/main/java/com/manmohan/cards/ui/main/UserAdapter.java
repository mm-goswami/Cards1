package com.manmohan.cards.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.manmohan.cards.R;
import com.manmohan.cards.data.model.User;
import com.manmohan.cards.databinding.UserItemBinding;
import com.manmohan.cards.di.ActivityContext;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private final List<User> userList = new ArrayList<>();

    private UserCallback userCallback;

    @Inject
    public UserAdapter(@ActivityContext Context context) {
    }

    public void setUserCallback(final UserCallback userCallback) {
        this.userCallback = userCallback;
    }

    public void setUserList(final List<User> userList) {
        if(userList!=null){
            this.userList.clear();
            this.userList.addAll(userList);
            notifyDataSetChanged();
        }
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        UserItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.user_item,
                        parent, false);
        return new UserViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        final User user = userList.get(position);
        holder.binding.setUser(user);
        holder.binding.executePendingBindings();
        holder.binding.setDescription(getDescription(user));
        holder.binding.acceptImg.setOnClickListener(v ->{
            holder.binding.setIsAccept(true);
            if(userCallback!=null)
                userCallback.onAccept(user);
        });
        holder.binding.declineImg.setOnClickListener(v -> {
            holder.binding.setIsDecline(true);
            if(userCallback!=null)
                userCallback.onDecline(user);
            });
        if(user.isDecline!=null)
            holder.binding.setIsDecline(user.isDecline);
        if(user.isAccept!=null)
            holder.binding.setIsAccept(user.isAccept);
    }

    private String getDescription(final User user) {
        return String.format("%s, %s, %s, %s, %s, %s",user.dob.age.toString(),
            user.location.street, user.location.city,
            user.location.state, user.location.timezone.description,
            user.location.postcode);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {

        final UserItemBinding binding;

        public UserViewHolder(UserItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface UserCallback{

        void onDecline(User user);

        void onAccept(User user);
    }
}
