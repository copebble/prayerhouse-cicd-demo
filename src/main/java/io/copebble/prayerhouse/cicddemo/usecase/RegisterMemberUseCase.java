package io.copebble.prayerhouse.cicddemo.usecase;

import io.copebble.prayerhouse.cicddemo.common.UseCase;
import io.copebble.prayerhouse.cicddemo.dto.MemberRegisterRequest;
import io.copebble.prayerhouse.cicddemo.dto.MemberRegisterResponse;
import io.copebble.prayerhouse.cicddemo.entity.Member;
import io.copebble.prayerhouse.cicddemo.service.MemberService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RegisterMemberUseCase {

  private final MemberService memberService;

  public MemberRegisterResponse execute(MemberRegisterRequest request) {
    Optional<Member> member = memberService.retrieveMember(request.getName());

    if (member.isPresent()) {
      throw new RuntimeException("Member already exists");
    }

    Member savedMember = memberService.registerMember(request.toEntity());

    return MemberRegisterResponse.of(savedMember);
  }
}
