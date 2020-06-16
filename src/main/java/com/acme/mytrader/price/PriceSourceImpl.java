package com.acme.mytrader.price;

import java.util.ArrayList;
import java.util.List;

public class PriceSourceImpl  implements PriceSource{

    private List<PriceListener> priceListeners;
    public PriceSourceImpl(){
        this.priceListeners = new ArrayList<>();
    }
    @Override
    public void addPriceListener(PriceListener listener) {
        this.priceListeners.add(listener);
    }

    @Override
    public void removePriceListener(PriceListener listener) {
        this.priceListeners.remove(listener);
    }

    @Override
    public void priceUpdate(String security, double price) {
        this.priceListeners.forEach(priceListener -> priceListener.priceUpdate(security, price));
    }
}
