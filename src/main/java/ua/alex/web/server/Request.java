package ua.alex.web.server;


import java.util.HashMap;
import java.util.Map;

public class Request {

    public String getUrl() {
        return url;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    public String findHeader(String header) {
        return headers.get(header);
    }

    private String url;
    private HttpMethod method;

    @Override
    public String toString() {
        return "Request{" +
                "url='" + url + '\'' +
                ", method=" + method +
                ", headers=" + headers +
                '}';
    }

    private Map<String, String> headers = new HashMap<>();
}
