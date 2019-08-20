package com.manmohan.cards.data.model;

import java.util.List;

public class BaseResponse<B> {
  public List<B> results;
  public ResponseInfo info;
}
