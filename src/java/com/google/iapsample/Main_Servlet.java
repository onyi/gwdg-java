package com.google.iapsample;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.SignatureException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

@SuppressWarnings("serial")
public class Main_Servlet extends HttpServlet {

    Seller_Info seller = new Seller_Info();
    final String ISSUER = seller.ISSUER;
    final String SIGNING_KEY = seller.SIGNING_KEY;

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        //Handles get requests.
        String token1 = null;
        try {
            //create JWT for the item
            JWT_Handler handler = new JWT_Handler(ISSUER, SIGNING_KEY);
            token1 = handler.getJWT();
            req.setAttribute("token", token1);
            //set and forward the HTTP request and response
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp?token=" + token1);
            if (dispatcher != null) {
                dispatcher.forward(req, resp);
            }

        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
        } catch (SignatureException e) {
            // TODO Auto-generated catch block
        } catch (ServletException e) {
            // TODO Auto-generated catch block
        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        //"Handles post request.
        String jwt = request.getParameter("jwt");
        String orderID;
        String jwt_response = new JWT_Handler(ISSUER, SIGNING_KEY).deserialize(jwt);
        JsonParser parser = new JsonParser();
        Gson gson = new GsonBuilder().create();
        JsonArray payload = parser.parse("[" + jwt_response + "]").getAsJsonArray();
        Payload payload_1 = gson.fromJson(payload.get(0), Payload.class);
        // validate the payment request and respond back to Google
        if (payload_1.iss_getter().equals("Google")
                && payload_1.aud_getter().equals(ISSUER)) {
            if (payload_1.response_getter() != null
                    && payload_1.response_getter().orderId_getter() != null) {
                orderID = payload_1.response_getter().orderId_getter();
                if (payload_1.request_getter().currencyCode_getter() != null
                        && payload_1.request_getter().sellerData_getter() != null
                        && payload_1.request_getter().name_getter() != null
                        && payload_1.request_getter().price_getter() != null) {
                    // optional - update local database
                    // respond back to complete payment
                    response.setStatus(200);
                    PrintWriter writer = response.getWriter();
                    writer.write(orderID);
                }
            }

        }



    }
}
