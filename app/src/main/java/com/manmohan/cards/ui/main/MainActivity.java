package com.manmohan.cards.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import com.manmohan.cards.R;
import com.manmohan.cards.data.model.User;
import com.manmohan.cards.databinding.ActivityMainBinding;
import com.manmohan.cards.ui.base.BaseActivity;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;
import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements UserAdapter.UserCallback{

  @Inject
  ViewModelProvider.Factory viewModelFactory;

  @Inject
  UserAdapter adapter;
  @Inject
  CardStackLayoutManager cardStackLayoutManager;

  private MainViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    getActivityComponent().inject(this);
    viewModel = ViewModelProviders.of(this, viewModelFactory)
        .get(MainViewModel.class);

    cardStackLayoutManager.setStackFrom(StackFrom.Bottom);
    binding.cardStackView.setLayoutManager(cardStackLayoutManager);
    binding.cardStackView.setAdapter(adapter);
    adapter.setUserCallback(this);

    subscribeLoading(viewModel);
    subscribeUserList(viewModel);
  }

  @Override public boolean onOptionsItemSelected(final MenuItem item) {
    switch (item.getItemId()) {
      case R.id.reload:
        reloadUserList();
        return true;
      case R.id.rewind:
        rewindUserList();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override public boolean onCreateOptionsMenu(final Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu, menu);
    return true;
  }

  @Override public void onDecline(User user) {
    viewModel.declineUser(user);
    SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
        .setDirection(Direction.Left)
        .setDuration(Duration.Normal.duration)
        .build();
    cardStackLayoutManager.setSwipeAnimationSetting(setting);
    binding.cardStackView.swipe();
  }

  @Override public void onAccept(User user) {
    viewModel.acceptUser(user);
    SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
        .setDirection(Direction.Right)
        .setDuration(Duration.Normal.duration)
        .build();
    cardStackLayoutManager.setSwipeAnimationSetting(setting);
    binding.cardStackView.swipe();
  }

  private void subscribeUserList(final MainViewModel viewModel) {
    viewModel.getUserList().observe(this, users -> {
      adapter.setUserList(users);
    });
  }

  private void subscribeLoading(final MainViewModel viewModel) {
    viewModel.getIsLoadingCartData().observe(this, this::showLoading);
  }

  private void showLoading(final boolean loading) {
    binding.setShowLoading(loading);
  }

  private void reloadUserList(){
    viewModel.reloadUserList();
  }

  private void rewindUserList(){
    adapter.notifyDataSetChanged();
  }

  @Override public int getLayoutId() {
    return R.layout.activity_main;
  }
}
