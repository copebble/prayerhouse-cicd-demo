package io.copebble.prayerhouse.cicddemo.repository;

import io.copebble.prayerhouse.cicddemo.entity.Member;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

  public static List<Member> storedMembers = new ArrayList<>();

  public Optional<Member> findByName(String name) {
    return storedMembers.stream()
        .filter(m -> m.getName().equals(name))
        .findFirst();
  }

  public Optional<Member> findById(Long id) {
    return storedMembers.stream()
        .filter(m -> m.getId().equals(id))
        .findFirst();
  }

  public Member save(Member member) {
    Long nextId = storedMembers.stream()
        .max(Comparator.comparing(Member::getId))
        .map(Member::getId)
        .orElse(1L);

    Member newMember = Member.builder()
        .id(nextId + 1L)
        .name(member.getName())
        .address(member.getAddress())
        .phoneNumber(member.getPhoneNumber())
        .build();

    storedMembers.add(newMember);

    return newMember;
  }
}
