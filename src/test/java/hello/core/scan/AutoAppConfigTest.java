package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.member.MemberService;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

class AutoAppConfigTest {

    @Test
    void basicScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

        MemberService memberService = ac.getBean(MemberService.class);
        OrderServiceImpl orderService = ac.getBean(OrderServiceImpl.class);

        System.out.println("orderService = " + orderService.getMemberRepository());
        System.out.println("orderService = " + orderService.getDiscountPolicy());

        Assertions.assertThat(memberService).isInstanceOf(MemberService.class);
        Assertions.assertThat(orderService).isInstanceOf(OrderService.class);
    }

}