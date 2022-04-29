package hello.advanced.v5;

import hello.advanced.trace.callback.TraceCallbackTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepositoryV5 {

    private final LogTrace trace;
    private final TraceCallbackTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.trace = trace;
        this.template = new TraceCallbackTemplate(trace);
    }

    public void save(String itemId) {

        TraceCallbackTemplate template = new TraceCallbackTemplate(trace);

        template.execute("OrderRepository.save()", () -> {
            if (itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            return null;
        });


    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
