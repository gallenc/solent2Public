package solent.ac.uk.ood.examples.cardcheck.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "resultCodes")
@XmlEnum
public enum ResultCodes {

    APPROVED, DECLINED, PRE_AUTHORISED, UNKNOWN_ACCOUNT
}
