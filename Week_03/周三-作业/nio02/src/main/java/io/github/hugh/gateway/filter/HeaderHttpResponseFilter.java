package io.github.hugh.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        //对responsse进行处理
        response.headers().set("props-add-from-response-filter", this.getClass().getSimpleName());
    }
}
