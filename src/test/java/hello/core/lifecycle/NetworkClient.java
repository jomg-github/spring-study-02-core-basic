package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient {
    private String url;

    public NetworkClient() {
        System.out.println("[생성자 호출]");
        callUrl();
    }

    public void setUrl(String url) {
        System.out.println("[setter 호출]");
        this.url = url;
        callUrl();
    }

    public void callUrl() {
        System.out.println("current URL : " + url);
    }

    @PostConstruct
    public void connect() {
        System.out.println("[connect 호출]");
        callUrl();
    }

    @PreDestroy
    public void disconnect() {
        System.out.println("[close 호출]");
        this.url = null;
        callUrl();
    }
}
