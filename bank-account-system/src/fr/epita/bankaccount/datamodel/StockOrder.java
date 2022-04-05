package fr.epita.bankaccount.datamodel;

import java.math.BigDecimal;
import java.util.Date;

public class StockOrder {
    BigDecimal price;
    Date date;
    BigDecimal commission;
    InvestmentAccount account;
    Stock stock;

}
