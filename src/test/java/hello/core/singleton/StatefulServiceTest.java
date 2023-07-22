package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    @DisplayName("싱글톤 객체에 공유 필드가 있을 경우 문제점")
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // A 사용자 10,000원 주문 (thread A)
        statefulService1.order("User A", 10000);

        // B 사용자 20,000원 주문 (thread B)
        statefulService1.order("User B", 20000);

        // A 사용자 주문 금액 조회
        int price1 = statefulService1.getPrice();
        Assertions.assertThat(price1).isNotEqualTo(10000);
    }


    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}