/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.oodd.cart.web;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.solent.com504.oodd.cart.model.dto.ShoppingItem;
import org.solent.com504.oodd.cart.model.service.Invoice;
import org.solent.com504.oodd.cart.model.service.ShoppingCart;
import org.solent.com504.oodd.cart.model.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MVCController {
    
    
    // this redirects calls to the root of our application to index.html 
    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
    public String index(Model model) {
        return "redirect:/index.html";
    }

    
    @RequestMapping(value = "/home", method = {RequestMethod.GET, RequestMethod.POST})
    public String home(
            @RequestParam(name = "action", required = false) String action,
            @RequestParam(name = "itemName", required = false) String itemName,
            @RequestParam(name = "itemUUID", required = false) String itemUUID,            
            @RequestParam(name = "itemQuantity", required = false) String itemQuantity,

            Model model,
            HttpSession session) {
        
        
        String message="";

        ShoppingService shoppingService = WebObjectFactory.getShoppingService();

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("shoppingCart");
        if (shoppingCart == null) {
            shoppingCart = WebObjectFactory.getNewShoppingCart();
            session.setAttribute("shoppingCart", shoppingCart);
        }

        if ("addItemToCart".equals(action)) {
            message = "adding "+itemName + " to cart";
            ShoppingItem shoppingItem = shoppingService.getNewItemByName(itemName);
            try {
               shoppingItem.setQuantity(Integer.parseInt(itemQuantity));
            }
            catch (NumberFormatException e)
            {
               shoppingItem.setQuantity(1);
            }
            message = "adding "+itemName + " to cart : "+shoppingItem;
            shoppingCart.addItemToCart(shoppingItem);
        }
        if ("removeItemFromCart".equals(action)) {
            message = "removing "+itemName + " from cart";
            shoppingCart.removeItemFromCart(itemUUID);
        } 
        if("purchaseItems".equals(action)){
            Invoice invoice = shoppingService.purchaseItems(shoppingCart);
            message = invoice.GetInvoiceAsString();
        }else {
            message = "action="+action;
        }

        double totalValue = shoppingCart.getTotal();
        
        List<ShoppingItem> items = shoppingService.getAvailableItems();        
        List<ShoppingItem> cartItems = shoppingCart.getShoppingCartItems();

        
        model.addAttribute("selectedPage","home");        
        model.addAttribute("message",message);        
        model.addAttribute("items",items);        
        model.addAttribute("cartItems",cartItems);        
        model.addAttribute("totalValue",totalValue);

        return "home";
    }
    

    /*
     * Default exception handler, catches all exceptions, redirects to friendly
     * error page. Does not catch request mapping errors
     */
    @ExceptionHandler(Exception.class)
    public String myExceptionHandler(final Exception e, Model model, HttpServletRequest request) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        final String strStackTrace = sw.toString(); // stack trace as a string
        String urlStr = "not defined";
        if (request != null) {
            StringBuffer url = request.getRequestURL();
            urlStr = url.toString();
        }
        model.addAttribute("requestUrl", urlStr);
        model.addAttribute("strStackTrace", strStackTrace);
        model.addAttribute("exception", e);
        //logger.error(strStackTrace); // send to logger first
        return "error"; // default friendly exception message for user
    }

}

