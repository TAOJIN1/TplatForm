package rpcclient.example.client;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import rpcclient.example.entry.Store;
import org.springframework.cloud.openfeign.FeignClient;
import java.util.List;

/**
 * 2 * @Author: Lanvo
 * 3 * @Date: 2020/7/30 14:51
 * 4
 */

@FeignClient(name="tplatform_endor/test")
public interface  StoreService {
    @RequestMapping(method = RequestMethod.GET, value = "/stores")
    List<Store> getStores();

    @RequestMapping(method = RequestMethod.POST, value = "/stores/{storeId}", consumes = "application/json")
    Store update(@PathVariable("storeId") Long storeId, Store store);
}

