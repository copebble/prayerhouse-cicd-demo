package io.copebble.prayerhouse.cicddemo.dto;

import io.copebble.prayerhouse.cicddemo.entity.Address;
import io.copebble.prayerhouse.cicddemo.entity.Member;
import io.copebble.prayerhouse.cicddemo.entity.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberRegisterRequest {

  private String name;
  private String postalCode;
  private String street;
  private String city;
  private String state;
  private String country;
  private String nationalCode;
  private String number;

  public Member toEntity() {
    return Member.builder()
        .name(name)
        .address(
            Address.builder()
                .postalCode(postalCode)
                .street(street)
                .city(city)
                .state(state)
                .country(country)
                .build()
        )
        .phoneNumber(
            PhoneNumber.builder()
                .number(number)
                .nationalCode(nationalCode)
                .build()
        )
        .build();
  }
}
