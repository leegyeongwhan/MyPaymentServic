package mypaymentservice.mypaymentservice.learningtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


class ClockTest {
    //Clock을 이용해서 LoccalDateTime.now 가져오기
    //Clock을 Test에서 사용할 때 내가 원하는 시간을 지정해서 현재 식나을 가져오게 할 수 있는가?

    @Test
    void clock() {
        Clock clock = Clock.systemDefaultZone();

        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);

        Assertions.assertThat(dt2.isAfter(dt1));
    }

    @Test
    void fixedclock() {
        Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());

        LocalDateTime dt1 = LocalDateTime.now(clock);
        LocalDateTime dt2 = LocalDateTime.now(clock);
        System.out.println(dt1);
        System.out.println(dt2);
        Assertions.assertThat(dt2.isAfter(dt1));
    }
}
