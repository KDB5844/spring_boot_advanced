package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.template.CallBack;
import hello.advanced.trace.strategy.code.template.TimeLongTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    /**
     * 템플릿 콜백 패턴 - 익명 내부 클래스, 람다
     */
    @Test
    void templateV1() {
        TimeLongTemplate template = new TimeLongTemplate();

        template.execute(new CallBack() {
            @Override
            public void call() {
                log.info("비즈니스 로직1");
            }
        });
        template.execute(() -> log.info("비즈니스 로직2"));

    }
}
