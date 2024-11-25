package edu.miu.cse.vsms.controller;

import edu.miu.cse.vsms.dto.request.EmployeeRequestDto;
import edu.miu.cse.vsms.dto.response.EmployeeResponseDto;
import edu.miu.cse.vsms.model.Employee;
import edu.miu.cse.vsms.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private EmployeeService employeeService;
    @Test
    void addEmployee() throws Exception {
        ///RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/employees");
        Employee employee = new Employee("edgar", "entende@miu.edu", "64111111",null);

        EmployeeRequestDto employeeRequestDto = new EmployeeRequestDto(employee.getName(),
                employee.getEmail(), employee.getPhone(), employee.getHireDate());

        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto((long)1, employee.getName(),
                employee.getEmail(), employee.getPhone(), employee.getHireDate(), null);

        Mockito.when(employeeService.addEmployee(employeeRequestDto))
                .thenReturn(employeeResponseDto);
        mockMvc.perform(
        MockMvcRequestBuilders.post("/api/v1/employees")
        .contentType(MediaType.APPLICATION_JSON)
        .content("")
        )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}