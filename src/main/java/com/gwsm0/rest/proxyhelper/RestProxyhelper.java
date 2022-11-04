package com.gwsm0.rest.proxyhelper;

import java.net.InetSocketAddress;
import java.net.Proxy;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestProxyhelper {

	// todo settare porta proxy
    private String proxyServerHost = "localhost:";
    /** @Value("${INET_PROXY_SERVER_PORT:none}")
     private String proxyServerPort; */

    private RestTemplate restTemplate;

    @PostConstruct private void init() {
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyServerHost, 9090));
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setProxy(proxy);
        restTemplate = new RestTemplate(requestFactory);
    }

    public <T> ResponseEntity<T> postForObject(String url, Object body, HttpHeaders httpHeaders, Class<T> clazz) {
        if(httpHeaders == null) httpHeaders = new HttpHeaders();
        HttpEntity<Object> entity = new HttpEntity<>(body, httpHeaders);
        return restTemplate.exchange(url, HttpMethod.POST, entity, clazz);
    }
}
