package hu.roszpapad.konyvklub.controllers;

import hu.roszpapad.konyvklub.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final TicketRepository ticketRepository;

    @RequestMapping({"/",""})
    public String getIndex(){

        return "index/index";
    }
}
