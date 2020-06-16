package com.acme.mytrader.strategy;

import com.acme.mytrader.execution.ExecutionService;
import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceListenerImpl;
import com.acme.mytrader.price.PriceSource;
import com.acme.mytrader.price.PriceSourceImpl;

/**
 * <pre>
 * User Story: As a trader I want to be able to monitor stock prices such
 * that when they breach a trigger level orders can be executed automatically
 * </pre>
 */
public class TradingStrategy {
    private PriceSource priceSource;
    private ExecutionService executionService;

    public void runStrategy(){
        this.priceSource = new PriceSourceImpl();
        this.executionService = new ExecutionService() {
            @Override
            public void buy(String security, double price, int volume) {
                System.out.println("Bought " + volume + " of " + security + " with price " + price);
            }

            @Override
            public void sell(String security, double price, int volume) {
                System.out.println("Sold " + volume + " of " + security + " with price " + price);
            }
        };
        PriceListener ibmPriceListener = new PriceListenerImpl(this.priceSource, this.executionService, "IBM", 100, 140.45);
        this.priceSource.priceUpdate("IBM", 200.3);
        this.priceSource.priceUpdate("IBM", 140);
    }

    public static void main(String[] args) {
        TradingStrategy tradingStrategy = new TradingStrategy();
        tradingStrategy.runStrategy();
    }
}
