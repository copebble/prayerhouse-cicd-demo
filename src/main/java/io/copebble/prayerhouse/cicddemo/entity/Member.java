package io.copebble.prayerhouse.cicddemo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class Member {

  private Long id;

  private String name;

  private Address address;

  private PhoneNumber phoneNumber;
}
