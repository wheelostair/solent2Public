/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.creditcardchecker.rest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.server.ResourceConfig;


@ApplicationPath("/rest")
public class RestApp extends ResourceConfig {
    public RestApp() {
        packages("org.solent.ood.creditcardchecker.rest");
    }
}
