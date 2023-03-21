package com.laderatech.stockservice.controller;

import com.laderatech.stockservice.service.StockService;
import com.laderatech.stockservice.vo.StockVo;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/stock")
public class StockController {

    private StockService stockService;

    @PostMapping
    private ResponseEntity<StockVo> createStock(@RequestBody StockVo stockVo){
        return new ResponseEntity<>(stockService.addStock(stockVo)
                            , HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    private ResponseEntity<StockVo> getStockById(@PathVariable("id") Long id){
        return ResponseEntity.ok(stockService.getStockById(id));
    }

    @GetMapping
    private ResponseEntity<List<StockVo>> findAllStocks(){
        return new ResponseEntity<>(stockService.findAllStocks(),HttpStatus.OK);
    }

    @PutMapping("{id}")
    private ResponseEntity<StockVo> updatedStock(@PathVariable("id") Long id,
                                                 @RequestBody StockVo stockVo){
        StockVo stockVoResponse=stockService.updateStock(id,stockVo);

        return new ResponseEntity<>(stockVoResponse,HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    private ResponseEntity<String> deleteStock(@PathVariable("id") Long id){
        stockService.deleteStockById(id);
        return new ResponseEntity<>("Stock Deleted Successfully",HttpStatus.OK);
    }

}
