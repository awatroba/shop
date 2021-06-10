package pay;

import org.springframework.web.servlet.ModelAndView;

/**
 * @author Angelika
 * Interface for all pay strategies.
 */
public interface PayStrategy {
    boolean pay(int paymentAmount);
    ModelAndView getModelAndView();
    void collectPaymentDetails();
    String getViewName();
    boolean verify();
}