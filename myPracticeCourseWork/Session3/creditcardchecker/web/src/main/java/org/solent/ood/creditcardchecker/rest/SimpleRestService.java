/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.ood.creditcardchecker.rest;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import solent.ac.uk.ood.examples.cardcheck.CardValidationResult;
import solent.ac.uk.ood.examples.cardcheck.RegexCardValidator;

/**
 *
 * @author cgallen
 */
@Path("/simple-api-v1")
public class SimpleRestService {

    @GET
    @Path("/hello")
    public String test() {
        return "Hello, REST!!!!";
    }

    @GET
    @Path("/time")
    public String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
        return sdf.format(new Date().getTime());
    }

    @GET
    @Path("/validateCard")
    public String validateCard(@QueryParam("cardNo") String card) {
        CardValidationResult result = RegexCardValidator.isValid(card);
        if (result.isValid()) {
            return result.getCardType().getIssuerName();
        } else {
            return result.getError();
        }
    }
}
