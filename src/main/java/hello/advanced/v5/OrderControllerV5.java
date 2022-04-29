package hello.advanced.v5;

import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceCallbackTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderControllerV5 {

    private final OrderServiceV5 orderService;
    private final LogTrace trace;
    private final TraceCallbackTemplate template;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace) {
        this.orderService = orderService;
        this.trace = trace;
        this.template = new TraceCallbackTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId) {

        template.execute("OrderController.request()", ()-> {
            orderService.orderItem(itemId);
            return null;
        });

        return "ok";
    }


}
