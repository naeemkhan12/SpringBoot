package com.naeem.landon.web.application;

import com.naeem.landon.business.domain.RoomReservation;
import com.naeem.landon.business.service.ReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


/**
 * Created by lenovo on 4/24/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ReservationControllers.class)
public class ReservationControllerTest {
    @MockBean
    private ReservationService reservationService;
    private MockMvc mockMvc;
    private  static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    @Test
    public void getReservations() throws  Exception{
        Date date = DATE_FORMAT.parse("2017-01-01");
        List<RoomReservation> roomReservationsList  = new ArrayList<>();
        RoomReservation mockRoomReservation = new RoomReservation();
        mockRoomReservation.setLastName("Test");
        mockRoomReservation.setFirstName("JUnit");
        mockRoomReservation.setGuestId(1);
        mockRoomReservation.setData(date);
        mockRoomReservation.setRoomNumber("J1");
        mockRoomReservation.setRoomId(100);
        mockRoomReservation.setRoomName("JUnit Room");
        roomReservationsList.add(mockRoomReservation);

        given(reservationService.getRoomReservationForDate(date)).willReturn(roomReservationsList);
            this.mockMvc.perform(get("/reservations?date=2017-01-01")).andExpect(status().isOk()).andExpect(content().string(
                    containsString("Test,JUnit")
            ));

    }
}
