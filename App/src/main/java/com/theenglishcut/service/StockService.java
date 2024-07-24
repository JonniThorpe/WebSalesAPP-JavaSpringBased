package com.theenglishcut.service;

import com.theenglishcut.dao.InventarioRepository;
import com.theenglishcut.dto.Stock;
import com.theenglishcut.entity.StockEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService extends DTOService<Stock, StockEntity>{
    @Autowired
    private InventarioRepository inventarioRepository;

    public void deleteByStockID(int stockID) {
        inventarioRepository.deleteById(stockID);
    }
}
