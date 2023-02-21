package com.example.tls.demo.server.webflux;

import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.boot.web.embedded.netty.SslServerCustomizer;
import org.springframework.boot.web.server.Http2;
import org.springframework.boot.web.server.Ssl;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettyConfig implements WebServerFactoryCustomizer<NettyReactiveWebServerFactory> {

    @Override
    public void customize(NettyReactiveWebServerFactory factory) {
        Ssl ssl = new Ssl();
        ssl.setEnabled(true);
        ssl.setKeyStoreType("PKCS12");
        ssl.setKeyStore("classpath:keystore/test_keystore.p12");
        ssl.setKeyStorePassword("changeit");
        ssl.setKeyAlias("rootCA");
        ssl.setKeyPassword("changeit");

        Http2 http2 = new Http2();
        http2.setEnabled(false);
        factory.addServerCustomizers(new SslServerCustomizer(ssl, http2, null));

    }
}
