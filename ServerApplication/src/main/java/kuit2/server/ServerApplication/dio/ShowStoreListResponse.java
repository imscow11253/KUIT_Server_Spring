package kuit2.server.ServerApplication.dio;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShowStoreListResponse {
    ShowStoreResponse[] list = new ShowStoreResponse[5];
    int index=0;
    public void addStoreInfo(ShowStoreResponse showStoreResponse){
        this.list[index++] = showStoreResponse;
    }
}
