package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field() throws InterruptedException {
        log.info("main start");
        Thread threadA = new Thread(() -> service.logic("userA"));
        Thread threadB = new Thread(() -> service.logic("userB"));

        threadA.setName("thread-A");
        threadB.setName("thread-B");

        threadA.start();
//        sleep(2000);      // 동시성 문제 X
        sleep(100);   // 동시성 문제 O ( FieldService 기준 )
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
