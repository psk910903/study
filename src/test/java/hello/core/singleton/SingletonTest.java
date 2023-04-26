package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
        //순수한 DI 컨테이너의 문제점을 확인해본다.
    void pureContatiner() {
        AppConfig appConfig = new AppConfig();
        //1. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        //2. 조회: 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 출력
        //memberService1 = hello.core.member.MemberServiceImpl@7bc1a03d
        //memberService2 = hello.core.member.MemberServiceImpl@70b0b186

        //memberService1 != memberService2

        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonService() {
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("singletonService1 = " + singletonService1);
        System.out.println("singletonService2 = " + singletonService2);

        //참조값 확인
        //singletonService1 = hello.core.singleton.SingletonService@78a2da20
        //singletonService2 = hello.core.singleton.SingletonService@78a2da20

        assertThat(singletonService1).isSameAs(singletonService2);
        // same: 참조확인
        // equal: 자바 equals

    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        //AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // 출력
        //memberService1 = hello.core.member.MemberServiceImpl@2141a12
        //memberService2 = hello.core.member.MemberServiceImpl@2141a12

        //memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
