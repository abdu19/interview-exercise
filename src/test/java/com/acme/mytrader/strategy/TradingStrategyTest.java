package com.acme.mytrader.strategy;

import com.acme.mytrader.price.PriceListener;
import com.acme.mytrader.price.PriceSource;
import com.acme.mytrader.price.PriceSourceImpl;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TradingStrategyTest {
    @Test
    public void testNothing() {
    }

    @Test
    public void testa(){
        PriceSource priceSource= new PriceSourceImpl();
        PriceListener priceListener = Mockito.mock(PriceListener.class);
        priceSource.addPriceListener(priceListener);
        Mockito.doAnswer(invocation -> {
            String security = invocation.getArgument(0);
            double price  = invocation.getArgument(1);
            Assert.assertEquals(security, "IBM");
            Assert.assertEquals(price, 200.01d, 0.1);
            return null;
        }).when(priceListener).priceUpdate("IBM", 200.01);
        priceSource.priceUpdate("IBM", 200.01);
    }
}
