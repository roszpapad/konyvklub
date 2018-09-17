package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.repositories.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private TicketRepository ticketRepository;

    public IndexController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @RequestMapping({"/",""})
    public String getIndex(){

        return "index/index";
    }
}
