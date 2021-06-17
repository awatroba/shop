package pay;

import com.awatroba.shop.helpers.PayRequest;
import org.springframework.web.servlet.ModelAndView;


/**
 * @author Angelika
 * Interface for all pay strategies.
 */
public interface PayStrategy {
    static String PAY_STRATEGY = "payStrategy";
    static String PAY_REQUEST = "payRequest";
    static String PAY_SUCCESS = "payRequest";

    boolean pay(PayRequest payRequest);
    ModelAndView getModelAndView(ModelAndView model);
    String getViewName();
    boolean verify(PayRequest payRequest);
}