package tests;

import base.Base;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RowerCheckTest extends Base {
    @Test
    public void rowerCheckTest() {
        homePage.closeCookiesWindow();
        homePage.searchParameter("Rower");
        assertEquals("Dla Ciebie wszystko - sprawdź nowe oferty!", homePage.getPageTitle());
        homePage.printFirstElementName();
        homePage.printFirstElementPrice();
        assertFalse(homePage.isPriceHigher500(), "The price is higher than 500PLN");
    }
}
