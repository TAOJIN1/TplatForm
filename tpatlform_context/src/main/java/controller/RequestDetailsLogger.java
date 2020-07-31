package controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.collect.ImmutableMap;
import contants.RequestAttributeConst;
import converter.jackson.OffsetDateTimeToIso8601Serializer;
import converter.jackson.StringToMapSerializer;
import utils.Jacksons;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author dehuisun
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class RequestDetailsLogger {

    @JsonIgnore
    private final HttpServletRequest request = ServletContextHolder.getRequest();

    @JsonProperty("request_id")
    private String requestId = ServletContextHolder.fetchRequestId();

    @JsonProperty("url")
    private String url = request.getRequestURL().toString();

    @JsonProperty("method")
    private String method = request.getMethod();

    @JsonProperty("params_map")
    private ImmutableMap<String, Object> paramsMap = fetParamsMap(request);

    @JsonProperty("headers")
    private ImmutableMap<String, Object> headers = fetchHttpHeaders(request);

    @JsonProperty("api_desc")
    private String apiDesc;

    @JsonProperty("request_body")
    @JsonSerialize(using = StringToMapSerializer.class)
    private String requestBody = (String) ServletContextHolder.getRequest().getAttribute(RequestAttributeConst.REQUEST_BODY_KEY);

    @JsonProperty("request_time")
    @JsonSerialize(using = OffsetDateTimeToIso8601Serializer.class)
    private OffsetDateTime requestTime = OffsetDateTime.now();

    @JsonProperty("response_time")
    @JsonSerialize(using = OffsetDateTimeToIso8601Serializer.class)
    private OffsetDateTime responseTime;

    @JsonProperty("character_encoding")
    private String characterEncoding = request.getCharacterEncoding();

    @JsonProperty("content_length")
    private long contentLength = request.getContentLengthLong();

    @JsonProperty("remote_host")
    private String remoteHost = request.getRemoteHost();

    @JsonProperty("remote_port")
    private int remotePort = request.getRemotePort();

    private ImmutableMap<String, Object> fetParamsMap(HttpServletRequest request) {
        final Map<String, String[]> parameterMap = request.getParameterMap();
        final ImmutableMap.Builder<String, Object> singleValueParams = ImmutableMap.builder();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
            singleValueParams.put(entry.getKey(), entry.getValue()[0]);
        }
        return singleValueParams.build();
    }

    private ImmutableMap<String, Object> fetchHttpHeaders(HttpServletRequest request) {
        final ImmutableMap.Builder<String, Object> headerBuilder = ImmutableMap.builder();
        final Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            final String headerName = headerNames.nextElement();
            headerBuilder.put(headerName, request.getHeader(headerName));
        }
        return headerBuilder.build();
    }

    @Override
    public String toString() {
        return Jacksons.parseInPrettyMode(this);
    }

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public ImmutableMap<String, Object> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(ImmutableMap<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public ImmutableMap<String, Object> getHeaders() {
		return headers;
	}

	public void setHeaders(ImmutableMap<String, Object> headers) {
		this.headers = headers;
	}

	public String getApiDesc() {
		return apiDesc;
	}

	public void setApiDesc(String apiDesc) {
		this.apiDesc = apiDesc;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public OffsetDateTime getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(OffsetDateTime requestTime) {
		this.requestTime = requestTime;
	}

	public OffsetDateTime getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(OffsetDateTime responseTime) {
		this.responseTime = responseTime;
	}

	public String getCharacterEncoding() {
		return characterEncoding;
	}

	public void setCharacterEncoding(String characterEncoding) {
		this.characterEncoding = characterEncoding;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public String getRemoteHost() {
		return remoteHost;
	}

	public void setRemoteHost(String remoteHost) {
		this.remoteHost = remoteHost;
	}

	public int getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	public HttpServletRequest getRequest() {
		return request;
	}
}
