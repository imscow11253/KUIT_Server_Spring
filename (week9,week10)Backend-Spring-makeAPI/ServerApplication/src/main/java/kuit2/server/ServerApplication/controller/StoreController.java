package kuit2.server.ServerApplication.controller;

import kuit2.server.ServerApplication.dio.ShowStoreListResponse;
import kuit2.server.ServerApplication.response.BaseResponse;
import kuit2.server.ServerApplication.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/stores")
@RestController
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("")
    public BaseResponse showListOfStore(@RequestParam long lastId){

        ShowStoreListResponse showStoreListResponse = this.storeService.getStoreList(lastId);

        return new BaseResponse(showStoreListResponse);
    }
}
