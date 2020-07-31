package response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.google.common.collect.ImmutableList;

import java.util.Collection;

/**
 * @author Lanvo
 */
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"}, ignoreUnknown = true)
public class ObjectCollectionResponse<T> extends RestfulResponse {
    private static final long serialVersionUID = 1862906172390850647L;

    private Collection<T> dataSet;

    public ObjectCollectionResponse(Collection<T> dataSet) {
        this.dataSet = ImmutableList.copyOf(dataSet);
    }
}
