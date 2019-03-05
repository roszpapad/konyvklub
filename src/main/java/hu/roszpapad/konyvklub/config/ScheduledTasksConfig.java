package hu.roszpapad.konyvklub.config;

import hu.roszpapad.konyvklub.model.Ticket;
import hu.roszpapad.konyvklub.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class ScheduledTasksConfig {

    private final TicketService ticketService;

    @Scheduled(cron = "0 1 1 * * ?")
    public void deleteExpiredTickets(){
        LocalDateTime now = LocalDateTime.now();
        List<Ticket> tickets = ticketService.getTickets();
        List<Ticket> deletableTickets = tickets.stream()
                .filter(ticket -> ticket.getEndDate() != null)
                .filter(ticket -> ticket.getEndDate().isBefore(now))
                .collect(Collectors.toList());

        deletableTickets.forEach(ticket -> ticketService.deleteTicket(ticket.getId(),true));
    }
}
