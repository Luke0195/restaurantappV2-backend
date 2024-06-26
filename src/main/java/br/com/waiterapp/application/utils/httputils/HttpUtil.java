package br.com.waiterapp.application.utils.httputils;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class HttpUtil {

    private HttpUtil(){}

    public static URI makeUriBuilderComponent(Object id){
        return  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }

    public static String getUrlToHttpServletRequest(HttpServletRequest request){
        return request.getRequestURI();
    }
}
