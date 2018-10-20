/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solent.ac.uk.ood.examples.hotellock.reception;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solent.ac.uk.ood.examples.hotellock.model.CardKey;
import solent.ac.uk.ood.examples.hotellock.model.HotelReceptionService;
import solent.ac.uk.ood.examples.hotellock.model.SecretKeyProvider;

/**
 *
 * @author cgallen
 */
public class HotelReceptionServiceImpl implements HotelReceptionService {
    public static final Logger LOG = LogManager.getLogger(HotelReceptionServiceImpl.class);
    public static final Logger TRANSACTIONLOG = LogManager.getLogger("transaction-log");

    private SecretKeyProvider secretKeyProvider;

    // OPTIONS FOR GENERATING AN ISSUE NUMBER
    
    // OPTION 1
    // NOTE access to m_issueNumber is not thread safe. How would we fix this using synchronized ?
    //    private static int m_issueNumber = 0;
    //       
    //    private int getNewIssueNumber(){
    //        m_issueNumber = m_issueNumber +1;
    //        return m_issueNumber;
    //    }
    
    // OPTION 2 USE AN AtomicInteger 
    // thread safe issue number
    AtomicInteger m_atomicIssueNumber = new AtomicInteger(0);

    private int getNewIssueNumber() {
        int issueNumber = m_atomicIssueNumber.incrementAndGet();
        return issueNumber;
    }
    
    // OPTION 3 alternative -  generate a randomIssueNumber
    private int getRandomIssueNumber(){
        int randomNum = ThreadLocalRandom.current().nextInt(1, Integer.MAX_VALUE - 1);
        return randomNum;
    }

    @Override
    public String createCardCode(String roomNumber, Date startDate, Date endDate) {

        int issueNumber = getNewIssueNumber();

        CardKey cardKey = new CardKey();
        cardKey.setRoomNumber(roomNumber);
        cardKey.setIssueNumber(issueNumber);
        cardKey.setStartDate(startDate);
        cardKey.setEndDate(endDate);
        System.out.println(cardKey);

        String cardString = secretKeyProvider.encodeCard(cardKey);

        String message = "Reception created new card from " + cardKey.toString() + " to encodedCard=" + cardString;

        // log card issued into debug log
        LOG.info(message);

        // log card issued into debug log
        TRANSACTIONLOG.info(message);
        return cardString;

    }

    @Override
    public CardKey readCard(String cardCode) {
        CardKey decodedCardKey = secretKeyProvider.decodeCard(cardCode);

        LOG.debug("Hotel reception decoded card " + decodedCardKey + " From encodedCard=" + cardCode);

        return decodedCardKey;
    }

    @Override
    public void setSecretKeyProvider(SecretKeyProvider secretKeyProvider) {
        this.secretKeyProvider = secretKeyProvider;
    }

}
