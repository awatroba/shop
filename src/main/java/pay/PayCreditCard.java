package pay;


import com.awatroba.shop.controllers.MyController;
import com.awatroba.shop.helpers.PayRequest;
import com.awatroba.shop.models.CreditCard;
import org.springframework.web.servlet.ModelAndView;


/**
 * Concrete strategy. Implements credit card payment method.
 */
public class PayCreditCard implements PayStrategy {
    private CreditCard card;
    private String viewName = "payCreditCard";

    //TODO to complete the method in the future


    @Override
    public boolean pay(PayRequest payRequest) {
        return false;
    }

    @Override
    public ModelAndView getModelAndView() {
        ModelAndView model= new ModelAndView(viewName);
        model.addObject(MyController.MESSAGE_ERROR, "");
        model.addObject(MyController.MESSAGE_SUCCESS, "");
        //model.addObject(PAY_REQUEST, new PayRequest());
        return model;
    }

    @Override
    public String getViewName() {
        return null;
    }

    @Override
    public boolean verify(PayRequest payRequest) {
        return false;
    }


}