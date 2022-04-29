package hello.advanced.v5;

import hello.advanced.trace.callback.TraceCallbackTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV5 orderRepository;
    private final LogTrace trace;
    private final TraceCallbackTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.trace = trace;
        this.template = new TraceCallbackTemplate(trace);
    }

    public void orderItem(String itemId) {

        TraceCallbackTemplate template = new TraceCallbackTemplate(trace);

        template.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });

    }

}
