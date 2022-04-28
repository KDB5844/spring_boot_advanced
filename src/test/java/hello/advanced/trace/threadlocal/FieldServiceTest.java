package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    @Test
    void field() throws InterruptedException {
        log.info("main start");
        Thread threadA = new Thread(() -> fieldService.logic("userA"));
        Thread threadB = new Thread(() -> fieldService.logic("userB"));

        threadA.setName("thread-A");
        threadB.setName("thread-B");

        threadA.start();
//        sleep(1000);      // 동시성 문제 X
        sleep(100);
        threadB.start();

        threadA.join();
        threadB.join();

        log.info("main end");

    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
