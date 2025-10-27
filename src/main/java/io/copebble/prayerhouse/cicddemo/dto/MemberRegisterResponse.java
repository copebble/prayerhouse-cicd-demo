package io.copebble.prayerhouse.cicddemo.dto;

import io.copebble.prayerhouse.cicddemo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberRegisterResponse {

  private Long id;
  private String name;

  public static MemberRegisterResponse of(Member member) {
    return new MemberRegisterResponse(member.getId(), member.getName());
  }
}
