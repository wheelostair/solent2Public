/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.creditcardchecker.client.test.manual;

import org.junit.Test;
import static org.junit.Assert.*;
import org.solent.ood.creditcardchecker.client.CardCheckerClient;
import org.solent.ood.creditcardchecker.model.dto.CreditCard;
import solent.ac.uk.ood.examples.cardcheck.CardValidationResult;

/**
 *
 * @author cgallen
 */


public class CardCheckerClientTest {

    @Test
    public void testGetClient() {

        CardCheckerClient cardCheckerClient = new CardCheckerClient("http://localhost:8080/creditcardchecker-web");

        boolean isValid = cardCheckerClient.getCheckCard("4444444444444448");
        assertTrue(isValid);

        isValid = cardCheckerClient.getCheckCard("4444444444444449");
        assertFalse(isValid);

    }

    @Test
    public void testPostClient() {
        
        CreditCard creditcard = new CreditCard();
        
        creditcard.setCardnumber("4444444444444448");
        
        CreditCard creditcard1 = new CreditCard();
        
        creditcard1.setCardnumber("1111111111111111");

        CardCheckerClient cardCheckerClient = new CardCheckerClient("http://localhost:8080/creditcardchecker-web");

        
        boolean isValid = cardCheckerClient.postCheckCard(creditcard).isValid();
        assertTrue(isValid);

        isValid = cardCheckerClient.postCheckCard(creditcard1).isValid();
        assertFalse(isValid);

    }

}
