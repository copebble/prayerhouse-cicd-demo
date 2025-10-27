package io.copebble.prayerhouse.cicddemo.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.util.ReflectionTestUtils.setField;

import io.copebble.prayerhouse.cicddemo.dto.MemberRegisterRequest;
import io.copebble.prayerhouse.cicddemo.dto.MemberRegisterResponse;
import io.copebble.prayerhouse.cicddemo.entity.Member;
import io.copebble.prayerhouse.cicddemo.service.MemberService;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegisterMemberUseCaseTest {

  @Mock
  private MemberService memberService;
  @InjectMocks
  private RegisterMemberUseCase registerMemberUseCase;

  @Test
  void alreadyExistedTest() {
    String givenName = "user1";
    Member alreadyExistedMember = Member
        .builder()
        .id(2L)
        .name(givenName)
        .build();

    given(memberService.retrieveMember("user1"))
        .willReturn(Optional.of(alreadyExistedMember));

    MemberRegisterRequest givenRequest = MemberRegisterRequest.builder()
        .name(givenName)
        .build();

    assertThrows(
        RuntimeException.class,
        () -> registerMemberUseCase.execute(givenRequest),
        "Member already existed"
    );
  }

  @Test
  void registerMemberTest() {
    String userName = "user1";
    Long givenId = 2L;
    MemberRegisterRequest registerRequest = MemberRegisterRequest
        .builder()
        .name("user1")
        .postalCode("1111")
        .street("Church St.")
        .city("Sydney")
        .state("NSW")
        .country("AU")
        .nationalCode("61")
        .number("111111111")
        .build();

    given(memberService.retrieveMember(userName))
        .willReturn(Optional.empty());

    given(memberService.registerMember(any()))
        .willAnswer(inv -> {
          Member m = registerRequest.toEntity();
          setField(m, "id", givenId);
          return m;
        });

    MemberRegisterResponse response = registerMemberUseCase.execute(registerRequest);

    assertThat(response.getId()).isEqualTo(givenId);
    assertThat(response.getName()).isEqualTo(registerRequest.getName());
  }
}