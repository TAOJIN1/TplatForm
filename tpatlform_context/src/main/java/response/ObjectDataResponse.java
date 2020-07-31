package response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Lanvo
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class ObjectDataResponse<T> extends RestfulResponse {
    private static final long serialVersionUID = 1862906172390850647L;

    private T data;

    public ObjectDataResponse(T data) {
        this.data = data;
    }
    
    public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
