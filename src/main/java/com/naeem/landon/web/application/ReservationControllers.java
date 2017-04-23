package com.naeem.landon.web.application;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lenovo on 4/23/2017.
 */
@Controller
@RequestMapping(value = "/reservations")
public class ReservationControllers {
    @RequestMapping(method = RequestMethod.GET)
    public String getReservations(){
        return "reservations";
    }


}
