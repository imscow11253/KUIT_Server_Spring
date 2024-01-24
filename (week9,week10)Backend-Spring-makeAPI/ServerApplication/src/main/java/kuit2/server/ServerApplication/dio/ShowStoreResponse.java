package kuit2.server.ServerApplication.dio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShowStoreResponse {
    long store_id;
    String name;
    double average_valuation;
    double minimum_acceptable_order;
}
