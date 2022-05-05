package com.revature.richbank.menus;

import com.revature.richbank.services.CustomerService;

import java.io.BufferedReader;

public class UpdateMenu extends Menu {

    private CustomerService customerService = new CustomerService();

    public UpdateMenu (BufferedReader terminalReader) {
        super("Update Menu", "/update", terminalReader);
    }

    @Override
    public void render() throws Exception {
        System.out.println("UpdateMenu::render() : rendering Menu");


    }
}
