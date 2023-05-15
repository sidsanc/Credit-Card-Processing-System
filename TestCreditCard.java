import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.creditCard.VisaCC;

public class TestCreditCard {
    public static void main(String[] args) {
        List<CreditCard> creditCards = new ArrayList<>();
        String csvFile = "cc.csv";
        String line = "";
        String cvsSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] creditCardRecord = line.split(cvsSplitBy);
                CreditCard creditCard = CreditCardFactory.createCreditCard(creditCardRecord);
                creditCards.add(creditCard);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (CreditCard creditCard : creditCards) {
            System.out.println(creditCard.toString());
        }
    }
}

abstract class CreditCard {
    private String cardNumber;
    private String expirationDate;
    private String cardHolderName;
    private String type = "";

    public CreditCard(String type, String cardNumber, String expirationDate, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cardHolderName = cardHolderName;
        this.type = type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "Type='" + type + '\'' +
                "cardNumber='" + cardNumber + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                '}';
    }
}

class VisaCC extends CreditCard {
    public VisaCC(String type,String cardNumber, String expirationDate, String cardHolderName) {
        super(type, cardNumber, expirationDate, cardHolderName);
    }
}

class MasterCC extends CreditCard {
    public MasterCC(String type, String cardNumber, String expirationDate, String cardHolderName) {
        super(type,cardNumber, expirationDate, cardHolderName);
    }
}


class AmExCC extends CreditCard {
    public AmExCC(String type,String cardNumber, String expirationDate, String cardHolderName) {
        super(type,cardNumber, expirationDate, cardHolderName);
    }
}

class DiscoverCC extends CreditCard {
    public DiscoverCC(String type,String cardNumber, String expirationDate, String cardHolderName) {
        super(type,cardNumber, expirationDate, cardHolderName);
    }
}

class CreditCardFactory {
    public static CreditCard createCreditCard(String[] creditCardRecord) {
        String cardNumber = creditCardRecord[0];
        String expirationDate = creditCardRecord[1];
        String cardHolderName = creditCardRecord[2];
        
        if (isVisaCC(cardNumber)) {
            return new VisaCC("VISA",cardNumber, expirationDate, cardHolderName);
        } else if (isMasterCC(cardNumber)) {
            return new MasterCC("MASTER",cardNumber, expirationDate, cardHolderName);
        } else if (isAmExCC(cardNumber)) {
            return new AmExCC("AMEX",cardNumber, expirationDate, cardHolderName);
        } else if (isDiscoverCC(cardNumber)) {
            return new DiscoverCC("DISCOVER",cardNumber, expirationDate, cardHolderName);
        } else {
            //System.out.println("Invalid credit card number" + cardNumber);
            //throw new IllegalArgumentException("Invalid credit card number");
            return null;
        }
    }


    private static boolean isVisaCC(String cardNumber) {
        return cardNumber.length() == 13 || cardNumber.length() == 16 && cardNumber.charAt(0) == '4';
    }

    private static boolean isMasterCC(String cardNumber) {
        return cardNumber.length() == 16 && cardNumber.charAt(0) == '5' && cardNumber.charAt(1) >= '1' && cardNumber.charAt(1) <= '5';
    }

    private static boolean isAmExCC(String cardNumber) {
        return cardNumber.length() == 15 && cardNumber.charAt(0) == '3' && (cardNumber.charAt(1) == '4' || cardNumber.charAt(1) == '7');
    }

    private static boolean isDiscoverCC(String cardNumber) {
        return cardNumber.length() == 16 && cardNumber.startsWith("6011");
    }
}

