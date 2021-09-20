package co.du.pay.vyne.filter;

import co.du.pay.vyne.controller.ControllerApiHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.RequestPath;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.web.util.pattern.PathPattern;
import org.springframework.web.util.pattern.PathPatternParser;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class CustomWebFilter implements WebFilter {

    List<PathPattern> pathPatternList;

    {
        PathPattern pathPattern = new PathPatternParser().parse("/transaction/retrieve/**");
        pathPatternList = new ArrayList<>();
        pathPatternList.add(pathPattern);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        RequestPath path = exchange.getRequest().getPath();

        if (pathPatternList
                .stream()
                .anyMatch(pathPattern -> pathPattern.matches(path.pathWithinApplication()))) {
            log.info("Path matches - do some cool logic maybe ... ");
            return chain.filter(exchange);
        }
        log.info("Path excluded : {} ", path.toString());
        return chain.filter(exchange);
    }
}
