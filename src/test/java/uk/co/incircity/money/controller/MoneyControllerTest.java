package uk.co.incircity.money.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import uk.co.incircity.money.ApplicationException;
import uk.co.incircity.money.model.MoneyTransfer;
import uk.co.incircity.money.service.MoneyService;

import java.math.BigDecimal;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MoneyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    MoneyService moneyService;

    @Test
    public void transfer_BadRequest_ErrorResponse() throws Exception {

        MoneyTransfer moneyTransfer = new MoneyTransfer.MoneyTransferBuilder()
                .fromAccountNumber(1)
                .toAccountNumber(2)
                .amount(new BigDecimal(100.00))
                .build();

        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonContent = objectMapper.writeValueAsString(moneyTransfer);

        when(moneyService.transferMoney(any(MoneyTransfer.class))).thenThrow(ApplicationException.class);

        mvc.perform(MockMvcRequestBuilders
                .post("/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void transfer_ProperRequest_SuccessResponse() throws Exception {

        MoneyTransfer moneyTransfer = new MoneyTransfer.MoneyTransferBuilder()
                .fromAccountNumber(1)
                .toAccountNumber(2)
                .amount(new BigDecimal(100.00))
                .build();

        final ObjectMapper objectMapper = new ObjectMapper();
        final String jsonContent = objectMapper.writeValueAsString(moneyTransfer);

        System.out.println(jsonContent);

        when(moneyService.transferMoney(any(MoneyTransfer.class))).thenReturn(true);

        mvc.perform(MockMvcRequestBuilders
                .post("/transfer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAccounts_ProperRequest_SuccessResponse() throws Exception {

        mvc.perform(MockMvcRequestBuilders
                .get("/accounts")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}
