package io.copebble.prayerhouse.cicddemo.service;

import io.copebble.prayerhouse.cicddemo.repository.MemberRepository;
import io.copebble.prayerhouse.cicddemo.entity.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository repository;

  public Optional<Member> retrieveMember(String name) {
    return repository.findByName(name);
  }

  public Member retrieveMemberById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Member not found"));
  }

  public Member registerMember(Member member) {
    return repository.save(member);
  }
}
