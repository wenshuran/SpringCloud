package ZuulGateway;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreRequestFilter extends ZuulFilter {
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {  //令牌桶
        RequestContext ctx = RequestContext.getCurrentContext();
        if (!RATE_LIMITER.tryAcquire()){
            System.out.println("超载");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(429);
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        System.out.println(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object token = request.getParameter("token");
        if (token == null){
            System.out.println("Empty Token!");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty");
            }catch (Exception e){}
            return null;
        }
        System.out.println(token);
        return null;
    }
}
