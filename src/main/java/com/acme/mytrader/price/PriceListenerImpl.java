package com.acme.mytrader.price;

import com.acme.mytrader.execution.ExecutionService;

public class PriceListenerImpl implements PriceListener{

    private String security;
    private int buyQuantity;
    private double threshold;
    private ExecutionService executionService;

    public PriceListenerImpl(PriceSource priceSource, ExecutionService executionService,
                             String security,
                             int buyQuantity, double threshold){
        priceSource.addPriceListener(this);
        this.security = security;
        this.buyQuantity = buyQuantity;
        this.threshold =threshold;
        this.executionService = executionService;
    }

    @Override
    public void priceUpdate(String security, double price) {
        if(security.equals(this.security) && (price<this.threshold)){
            executionService.buy(this.security, price, this.buyQuantity);
        }
    }
}
