/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.coxa.com528.ood;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author ally cox
 */
public class MyTestClassTest {

    @Test
    public void shouldAnswerWithTrue() {
        MyTestClassLog4j myTest = new MyTestClassLog4j();
        myTest.writeAboutMe();
    }

    @Test
    public void shouldAnswerWithName() {
        MyTestClassLog4j myTest = new MyTestClassLog4j();
        String result = myTest.talkAboutMe("Ally");
        System.out.println(result);
        
        assertTrue("Talking about Ally".equals(result));
                
                
    }
}
