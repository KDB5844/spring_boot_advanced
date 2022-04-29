package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {

    /**
     * 전략 패턴 사용
     */
    @Test
    void strategyV1() {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(StrategyLogic1::new);
        contextV2.execute(StrategyLogic2::new);
    }

    /**
     * 전략 패턴 익명 내부 클래스, Lambda
     */
    @Test
    void strategyV2() {
        ContextV2 contextV2 = new ContextV2();
        contextV2.execute(new Strategy() {
            @Override
            public void call() {
                System.out.println("비즈니스 로직1");
            }
        });

        contextV2.execute(()-> System.out.println("비즈니스 로직2"));
    }

}
