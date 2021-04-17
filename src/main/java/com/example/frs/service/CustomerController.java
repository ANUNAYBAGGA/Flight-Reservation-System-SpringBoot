package com.example.frs.service;
import com.example.frs.bean.*;
import com.example.frs.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.Date;
import java.text.SimpleDateFormat;



@Controller
public class CustomerController {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private FlightDao flightDao;
    @Autowired
    private ScheduleDao scheduleDao;
    @Autowired
    private ReservationDao reservationDao;
    @Autowired
    private CardDao cardDao;
    @Autowired
    ProfileDao profileDao;

    @GetMapping("/findFlight")
    public String render(Model model){
        return "viewScheduleByRoute_new";
    }

    @PostMapping("/findFlight")
    public String add(Model model, @RequestParam("source")String source, @RequestParam("destination")String destination, @RequestParam("date")String date, HttpServletRequest request) throws ParseException {
        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date);
        Date today = new Date();
        System.out.println(date1 + " " + today);
        System.out.println(date1.compareTo(today));
        if (date1.compareTo(today)<=0){
            System.out.println("Date is already over. Request invalid");
            model.addAttribute("msg_date", "Date is already over. Request invalid");
            return "viewScheduleByRoute_new";
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        String bookingDate = dtf.format(now);

        System.out.println(date);

        List<RSF> temp =  customerDao.getSchedule(source, destination, date);
        model.addAttribute("rWSF", temp);
        model.addAttribute("journeyDate", date);
        model.addAttribute("bookingDate", bookingDate);
        return "resultScheduleByRoute_new";
    }


    @GetMapping("/addReservation")
    public String view_reservation(Model model, HttpServletRequest request,
                                   @RequestParam("journeyDate")String journeyDate,
                                   @RequestParam("bookingDate")String bookingDate,
                                   @RequestParam("fare")double fare,
                                   @RequestParam("schedule_id")String schedule_id)
    {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("schedule_id", schedule_id);
        model.addAttribute("journeyDate", journeyDate);
        model.addAttribute("bookingDate", bookingDate);
        model.addAttribute("fare", fare);
        return "addReservation_new";
    }

    @PostMapping("/addReservation")
    public String add(Model model, @RequestParam("scheduleID")String scheduleID,
                      @RequestParam("reservationType")String reservationType,
                      @RequestParam("journeyDate")String journeyDate,
                      @RequestParam("noSeats")int noSeats,
                      @RequestParam("bookingDate")String bookingDate,
                      @RequestParam("fare")double fare,
                      HttpServletRequest request
    ) throws ParseException {

        String user_ID = (String) request.getSession().getAttribute("SESSION_UID");
        String id = UUID.randomUUID().toString();
        //System.out.println(scheduleID);
        Schedule schedule= scheduleDao.get(scheduleID);
        //System.out.println(schedule.get(0).getFlight_id());
        Flight flight = flightDao.get(schedule.getFlight_id());
        //System.out.println(flight.get(0).getFlight_name());
        if (flight.getSeating_capacity() < flight.getReservation_capacity() + noSeats){
            System.out.println("More seats booked than total capacity");
        }
        else{
            flight.setReservation_capacity(flight.getReservation_capacity() + noSeats);
            flightDao.update(flight);
        }

        double fares = fare * noSeats;
        String schedule_id = scheduleID;
        String reservation_type = reservationType;
        Reservation reservation  = new Reservation(id, user_ID, schedule_id, reservationType, bookingDate, journeyDate, noSeats, fares, 1);
        customerDao.reserveTicket(reservation);

        Passengers pass = new Passengers();
        for(int i = 0; i < noSeats; ++i)
            pass.addPassenger(new Passenger("","", "", "", 1, i));
        model.addAttribute("form", pass);
        model.addAttribute("uid", id);

        return "addPassenger_new";
    }


    @PostMapping("/addPassenger")
    public String addPassenger(Model model, @ModelAttribute("form") Passengers pass, HttpServletRequest request, @RequestParam("id") String id){
        List<Passenger> passengers = pass.getPassengers();
        String s = id;
        String str[] = s.split(",");
        String reservation_ID = str[0];
        for (Passenger p : passengers) {
            String rand_id = UUID.randomUUID().toString();
            p.setReservation_id(reservation_ID);
            p.setUnique_id(rand_id);
            System.out.println(p.getName());
        }
        customerDao.savePassenger(passengers);
        String userID = (String)request.getSession().getAttribute("SESSION_UID");
        Card card = cardDao.getCardNo(userID);
        System.out.println(card.getCard_no()+" "+card.getCvv());
        model.addAttribute("card_no", card.getCard_no());
        model.addAttribute("cvv", card.getCvv());
        model.addAttribute("valid_to", card.getValid_to());
        model.addAttribute("credit_balance", card.getCredit_balance());
        model.addAttribute("user_id",card.getUser_id());
        return "payments_new";
    }

    @GetMapping("/viewBookedTicket")
    public String view_ticket(Model model){
        List<reservationSchedule> SWR =  customerDao.viewBookedTicket();
        model.addAttribute("SWR", SWR);
        return "viewBookedTicket_new";
    }
    @GetMapping("/deleteReservation")
    public String delete_ticket(Model model,@RequestParam("reservationID")String reservationID
            ,@RequestParam("journey_date") String journey_date,@RequestParam("flightID")String flightID,
                                @RequestParam("noSeats")int seats) throws ParseException {

        Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(journey_date);
        Date today = new Date();

        if (date1.compareTo(today)<=0){
            System.out.println("Date is already over. Request invalid");
            List<reservationSchedule> SWR =  customerDao.viewBookedTicket();
            model.addAttribute("SWR", SWR);
            return "viewBookedTicket_new";
        }

        Flight fl = flightDao.get(flightID);
        fl.setReservation_capacity(fl.getReservation_capacity() - seats);
        flightDao.update(fl);
        customerDao.deleteBookedTicket(reservationID);
        List<reservationSchedule> SWR =  customerDao.viewBookedTicket();
        model.addAttribute("SWR", SWR);
        return "viewBookedTicket_new";
    }

    @GetMapping("/payments")
    public String view_payment(Model model, HttpServletRequest request){
        return "payments_new";
    }

    @PostMapping("/payments")
    public String view_ticket_after(Model model){
        List<reservationSchedule> SWR =  customerDao.viewBookedTicket();
        model.addAttribute("SWR", SWR);
        return "viewBookedTicket_new";
    }
    @GetMapping("/printTicket")
    public String printTicket(Model model, HttpServletRequest request,
                              @RequestParam("date") String date, @RequestParam("time") String time,
                              @RequestParam("source") String source,@RequestParam("destination") String destination,
                              @RequestParam("fname") String fname,@RequestParam("id") String id){
        String user_id = (String)request.getSession().getAttribute("SESSION_UID");
        Profile profile = profileDao.getByUserId(user_id);
        System.out.println(profile.getFirst_name() + " " + fname + " " + date);
        model.addAttribute("profile_firstName", profile.getFirst_name());
        model.addAttribute("profile_lastName", profile.getLast_name());
        model.addAttribute("date", date);
        model.addAttribute("time", time);
        model.addAttribute("source", source);
        model.addAttribute("destination", destination);
        model.addAttribute("fname", fname);
        model.addAttribute("id", id);
        return "printTicket";
    }


}
