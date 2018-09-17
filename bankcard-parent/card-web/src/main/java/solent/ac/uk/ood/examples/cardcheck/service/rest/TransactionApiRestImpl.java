package solent.ac.uk.ood.examples.cardcheck.service.rest;

import javax.ws.rs.Consumes;
import org.anvard.jaxrs.server.*;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import solent.ac.uk.ood.examples.cardcheck.model.ResultCodes;

import solent.ac.uk.ood.examples.cardcheck.model.Transaction;
import solent.ac.uk.ood.examples.cardcheck.model.TransactionResult;

@Path("/transaction")
public class TransactionApiRestImpl {

//POST http://localhost:8680/rest/transaction/requestPreAuthorisation
//Accept: application/json
//Content-Type: application/json
//{
//    "timestamp": null,
//    "transactionId": null,
//    "from": null,
//    "to": null,
//    "amount": null,
//    "cardFrom": null,
//    "cardTo": null,
//    "id": null
//}
    //  public TransactionResult requestPreAuthorisation(Transaction requestTransaction);
    @POST
    @Path("/requestPreAuthorisation")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public TransactionResult requestPreAuthorisation(Transaction requestTransaction) {
        TransactionResult result = new TransactionResult();
        result.setResultCode(ResultCodes.DECLINED);
        result.setTransaction(requestTransaction);
        return result;
    }

    //  public TransactionResult requestTransaction(Transaction transactionRequest);
    @POST
    @Path("/requestTransaction")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public TransactionResult requestTransaction(Transaction requestTransaction) {
        TransactionResult result = new TransactionResult();
        result.setResultCode(ResultCodes.DECLINED);
        result.setTransaction(requestTransaction);
        return result;
    }

}
