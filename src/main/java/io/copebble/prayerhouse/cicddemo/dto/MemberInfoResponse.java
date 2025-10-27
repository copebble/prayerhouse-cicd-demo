package io.copebble.prayerhouse.cicddemo.dto;

import io.copebble.prayerhouse.cicddemo.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberInfoResponse {
  private final Long id;
  private final String name;

  public static MemberInfoResponse of(Member member) {
    return new MemberInfoResponse(member.getId(), member.getName());
  }
}
