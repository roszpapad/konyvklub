package hu.roszpapad.konyvklub.commands;

public class BookCommand {

    private Long id;
    private String title;
    private String writer;
    private String publisher;
    private Integer yearOfPublishing;
    private UserCommand owner;
    private TicketCommand sellingTicket;
    private OfferCommand offer;

    public BookCommand() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Integer getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(Integer yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }

    public UserCommand getOwner() {
        return owner;
    }

    public void setOwner(UserCommand owner) {
        this.owner = owner;
    }

    public TicketCommand getSellingTicket() {
        return sellingTicket;
    }

    public void setSellingTicket(TicketCommand sellingTicket) {
        this.sellingTicket = sellingTicket;
    }

    public OfferCommand getOffer() {
        return offer;
    }

    public void setOffer(OfferCommand offer) {
        this.offer = offer;
    }
}
