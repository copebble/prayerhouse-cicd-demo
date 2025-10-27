package io.copebble.prayerhouse.cicddemo.controller;

import io.copebble.prayerhouse.cicddemo.dto.MemberInfoResponse;
import io.copebble.prayerhouse.cicddemo.dto.MemberRegisterRequest;
import io.copebble.prayerhouse.cicddemo.dto.MemberRegisterResponse;
import io.copebble.prayerhouse.cicddemo.usecase.GetMemberUseCase;
import io.copebble.prayerhouse.cicddemo.usecase.RegisterMemberUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

  private final RegisterMemberUseCase registerMemberUseCase;
  private final GetMemberUseCase getMemberUseCase;

  @PostMapping("/register")
  public ResponseEntity<MemberRegisterResponse> register(
      @RequestBody MemberRegisterRequest request
  ) {
    MemberRegisterResponse registeredMember = registerMemberUseCase.execute(request);

    return ResponseEntity.ok(registeredMember);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MemberInfoResponse> getMemberById(@PathVariable Long id) {
    MemberInfoResponse memberInfo = getMemberUseCase.execute(id);

    return ResponseEntity.ok(memberInfo);
  }
}
