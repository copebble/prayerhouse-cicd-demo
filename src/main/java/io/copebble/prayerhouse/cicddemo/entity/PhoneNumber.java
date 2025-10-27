package io.copebble.prayerhouse.cicddemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PhoneNumber {

  private String nationalCode;
  private String number;
}
