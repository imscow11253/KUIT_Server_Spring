package kuit2.server.ServerApplication.service;

import kuit2.server.ServerApplication.dao.StoreDao;
import kuit2.server.ServerApplication.dio.ShowStoreListResponse;
import kuit2.server.ServerApplication.dio.ShowStoreResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreDao storeDao;
    public ShowStoreListResponse getStoreList(long lastId) {

        ShowStoreListResponse showStoreListResponse = new ShowStoreListResponse();

        for(int i=0;i<5;i++){
            if(this.storeDao.isThereStoreId(lastId+i)){
                String name = this.storeDao.getStoreNameById(lastId+i);
                double average_valuation = this.storeDao.getAverageValuationById(lastId+i);
                double minimum_acceptable_order = this.storeDao.getMinimumAcceptableOrderById((lastId+i));
                showStoreListResponse.addStoreInfo(new ShowStoreResponse(lastId+i, name, average_valuation, minimum_acceptable_order));
            }
            //else showStoreListResponse.addStoreInfo(new ShowStoreResponse(123,"장충동왕족발", 5,12.5));
            else break;
        }

        return showStoreListResponse;

    }
}
