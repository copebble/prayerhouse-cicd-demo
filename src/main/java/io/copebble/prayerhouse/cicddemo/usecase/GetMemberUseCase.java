package io.copebble.prayerhouse.cicddemo.usecase;

import io.copebble.prayerhouse.cicddemo.common.UseCase;
import io.copebble.prayerhouse.cicddemo.dto.MemberInfoResponse;
import io.copebble.prayerhouse.cicddemo.entity.Member;
import io.copebble.prayerhouse.cicddemo.service.MemberService;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetMemberUseCase {
  private final MemberService memberService;

  public MemberInfoResponse execute(Long id) {
    Member member = memberService.retrieveMemberById(id);

    return MemberInfoResponse.of(member);
  }
}
