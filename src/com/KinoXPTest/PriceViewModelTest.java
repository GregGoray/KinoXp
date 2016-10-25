package com.KinoXPTest;

import com.KinoXP.model.ExtrasModel;
import com.KinoXP.model.LoginViewModel;
import com.KinoXP.model.PriceViewModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class PriceViewModelTest {


    LoginViewModel loginViewModel = new LoginViewModel();
    @Before
    public void connectToDatabase() {
        loginViewModel.connectToDatabase();
    }
    @Test
    public void testPriceAddingUpdating(){
        PriceViewModel pr = new PriceViewModel();
        assertNotEquals(null,pr.retrievePricesOfExtras("Candy"));

    }
    @Test
    public void testInsertion(){
        PriceViewModel pr = new PriceViewModel();
        pr.changePrices("Soda", 400,200);
        ExtrasModel extra = new ExtrasModel("Soda",400,200);
        assertEquals(extra.getCategory(),pr.retrievePricesOfExtras("Soda").getCategory());
        assertEquals(extra.getLargePrice(),pr.retrievePricesOfExtras("Soda").getLargePrice());
        assertEquals(extra.getSmallPrice(),pr.retrievePricesOfExtras("Soda").getSmallPrice());
    }

}