package pay;

import com.awatroba.shop.controllers.MyController;
import com.awatroba.shop.enums.PayMethod;
import com.awatroba.shop.helpers.BlikRequest;
import com.awatroba.shop.helpers.PayRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Angelika
 * Implements PayPal payment method.
 */
public class PayBlik implements PayStrategy {
    private static String pattern = "\\d{4}";
    private String viewName = "payBlik";

    //TODO to complete the method in the future
    @Override
    public boolean pay(PayRequest payRequest) {
        return true;
    }

    @Override
    public ModelAndView getModelAndView() {
        ModelAndView model = new ModelAndView(viewName);
        model.addObject(MyController.MESSAGE_ERROR, "");
        model.addObject(MyController.MESSAGE_SUCCESS, "");
        model.addObject(PAY_REQUEST, new BlikRequest());
        model.addObject(PAY_STRATEGY, PayMethod.BLIK.getPayStrategy());
        return model;
    }

    @Override
    public String getViewName() {
        return viewName;
    }

    //TODO to complete the method in the future
    @Override
    public boolean verify(PayRequest payRequest) {
        return true;
    }


}