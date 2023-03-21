package com.laderatech.stockservice.mapper;

import com.laderatech.stockservice.model.Stock;
import com.laderatech.stockservice.vo.StockVo;

public interface StockMspper {

    StockVo mapToStockVo(Stock stock);

    Stock mapToStock(StockVo stockVo);
}
