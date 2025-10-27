package io.copebble.prayerhouse.cicddemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Address {

  private String postalCode;
  private String street;
  private String city;
  private String state;
  private String country;
}
