package com.example.rmmservices.controller;

import com.example.rmmservices.controller.bean.AccountServiceSaveCommand;
import com.example.rmmservices.entity.AccountService;
import com.example.rmmservices.service.AccountServiceCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
public class AccountServiceCommandController {

    private final AccountServiceCommandService accountServiceCommandService;

    @Autowired
    public AccountServiceCommandController(AccountServiceCommandService accountServiceCommandService) {
        this.accountServiceCommandService = accountServiceCommandService;
    }

    @PostMapping("/customer/{customerId}/services/new")
    public Response add(@PathVariable Long customerId, @RequestBody AccountServiceSaveCommand newService) {
        try {
            AccountService accountService = accountServiceCommandService.add(customerId, newService.getServiceId());
            return Response.ok(accountService.getId()).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PostMapping("/customer/{customerId}/services/{serviceId}/delete")
    public Response delete(@PathVariable Long customerId, @PathVariable Long serviceId) {
        accountServiceCommandService.delete(customerId, serviceId);
        return Response.ok().build();
    }
}
