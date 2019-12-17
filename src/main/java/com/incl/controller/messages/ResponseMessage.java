package com.incl.controller.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseMessage {
  POST_CREATED("Post successfully created"),
  USER_SUBSCRIBED("User successfully subscribed");
  
  private String message;
}
