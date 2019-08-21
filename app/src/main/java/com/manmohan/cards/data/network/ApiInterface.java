package com.manmohan.cards.data.network;

import com.manmohan.cards.data.model.BaseResponse;
import com.manmohan.cards.data.model.User;
import io.reactivex.Observable;
import retrofit2.http.GET;

import static com.manmohan.cards.data.network.Constant.USER_URL;

/**
 * Created by manmohan.
 * For all other apis dependencies
 */

public interface ApiInterface {

  @GET(USER_URL)
  Observable<BaseResponse<User>> getUserList();
}
