package com.laderatech.stockservice.serviceImpl;

import com.laderatech.stockservice.exception.ResourceNotFoundException;
import com.laderatech.stockservice.mapperImpl.StockMapperImpl;
import com.laderatech.stockservice.model.Stock;
import com.laderatech.stockservice.repository.StockRepository;
import com.laderatech.stockservice.service.StockService;
import com.laderatech.stockservice.vo.StockVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapperImpl stockMapper;

    @Override
    public StockVo addStock(StockVo stockVo) {

        Stock stock = stockMapper.mapToStock(stockVo);
        stock.setCreatedBy("admin");

        Stock savedStock=stockRepository.save(stock);

        StockVo savedStockVo=stockMapper.mapToStockVo(savedStock);


        return savedStockVo;

    }

    @Override
    public StockVo getStockById(Long id) {
        Optional<Stock> stock= stockRepository.findById(id);
        Stock stockId=stock.get();
        return stockMapper.mapToStockVo(stockId);
    }

    @Override
    public List<StockVo> findAllStocks() {
        List<Stock> stockList = stockRepository.findAll();
        return stockList.stream()
                .map(mapToStockVo->stockMapper.mapToStockVo(mapToStockVo))
                .collect(Collectors.toList());
    }

    @Override
    public StockVo updateStock(Long id, StockVo stockVo) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock","id",id));
        stock.setProductCode(stockVo.getProductCode());
        stock.setProductQuantity(stockVo.getProductQuantity());
        stock.setLocation(stockVo.getLocation());
        stock.setZipCode(stockVo.getZipCode());
        stock.setUpdatedBy("admin");

        Stock updatedStock = stockRepository.save(stock);

        return stockMapper.mapToStockVo(updatedStock);
    }

    @Override
    public void deleteStockById(long id) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock","id",id));

        stockRepository.delete(stock);
    }


}
