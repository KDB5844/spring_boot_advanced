package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class ThreadLogTraceTest {

    ThreadLocalLogTrace threadLocalLogTrace = new ThreadLocalLogTrace();

    @Test
    void begin_end_level2() {

        Runnable userA = () -> startLog("userA1", "userA2");
        Runnable userB = () -> startLog("userB1", "userB2");

        Thread thread1 = new Thread(userA);
        Thread thread2 = new Thread(userB);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    private void startLog(String message1, String message2) {
        TraceStatus status1 = threadLocalLogTrace.begin(message1);
        TraceStatus status2 = threadLocalLogTrace.begin(message2);
        threadLocalLogTrace.end(status2);
        threadLocalLogTrace.end(status1);
    }

    @Test
    void begin_exception_level2() {
        TraceStatus status1 = threadLocalLogTrace.begin("hello1");
        TraceStatus status2 = threadLocalLogTrace.begin("hello2");
        threadLocalLogTrace.exception(status2, new IllegalStateException());
        threadLocalLogTrace.exception(status1, new IllegalStateException());
    }


    @Test
    void end() {
    }

    @Test
    void exception() {
    }
}