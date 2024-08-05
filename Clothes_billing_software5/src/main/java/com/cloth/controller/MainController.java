package com.cloth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cloth.Repository.Payment_Repo;
import com.cloth.ServiceImpl.Customer_Service_impl;
import com.cloth.ServiceImpl.Money_b_service;
import com.cloth.ServiceImpl.Stock_Service_impl;
import com.cloth.ServiceImpl.Tailor_Service_impl;

import com.cloth.model.Customer;
import com.cloth.model.Money_b;
import com.cloth.model.PaymentInfo;
import com.cloth.model.Stock_info;
import com.cloth.model.Tailor;

@Controller
public class MainController {

    @Autowired
    private Stock_Service_impl stockService;

    @Autowired
    private Customer_Service_impl customerService;

    @Autowired
    private Money_b_service moneyService;

    @Autowired
    private Tailor_Service_impl tailorService;

    @Autowired
    private Payment_Repo paymentRepo;

    // Dashboard and Login
    @GetMapping("/Dashboard")
    public String dashboard() {
        return "Dashboard";
    }

    @GetMapping("/Dashboard_login")
    public String login() {
        return "Dashboard_login";
    }

    @GetMapping("/workerDashboard")
    public String workerdashboard() {
        return "workerlogin";
    }

    // Stock Management
    // This section will be moved to StockController
    @GetMapping("/stockForm")
    public String showStockForm(Model model) {
        model.addAttribute("stock", new Stock_info());
        return "Stock";
    }

    @PostMapping("/SaveStock")
    public String saveStock(@ModelAttribute("stock") Stock_info stock) {
        stockService.Save_Stock(stock);
        return "redirect:/stock";
    }

    @GetMapping("/stock")
    public String showStockPage(Model model) {
        List<Stock_info> stockList = stockService.getAllStock();
        model.addAttribute("stockList", stockList);
        return "Stock_display";
    }

    // Customer Management
    // This section will be moved to CustomerController
    @GetMapping("/CustomerForm")
    public String showCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "CustomerPage";
    }

    @PostMapping("/SaveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer customer, Model model) {
        try {
            customerService.Save_Customer(customer);
            return "redirect:/Dashboard";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("customer", customer);
            return "CustomerPage";
        }
    }

    @GetMapping("/Customers")
    public String getCustomerList(Model model) {
        List<Customer> customerList = customerService.getAllCustomer();
        model.addAttribute("customerList", customerList);
        return "Cust_display";
    }

    @GetMapping("/editCustomer/{id}")
    public String showEditCustomerForm(@PathVariable Long id, Model model) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        model.addAttribute("customer", customer);
        return "EditCustomer";
    }

    @PostMapping("/updateCustomer/{id}")
    public String updateCustomer(@PathVariable("id") Long id, @ModelAttribute("customer") Customer updatedCustomer) {
        customerService.updateCustomer(updatedCustomer);
        return "redirect:/Customers";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deleteCustomer(id);
        return "redirect:/Customers";
    }

    // Money Management
    // This section will be moved to MoneyController
    @GetMapping("/Money_b")
    public String showMoney_b(Model model) {
        model.addAttribute("money", new Money_b());
        return "Money_Page";
    }

    @PostMapping("/submit_money")
    public String saveMoney(@Validated @ModelAttribute("money") Money_b money_b, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "Money_Page";
        }
        moneyService.moneySave(money_b);
        return "redirect:/Dashboard";
    }

    @GetMapping("/money_bs")
    public String getMoney_bList(Model model) {
        List<Money_b> moneyList = moneyService.getAllMoney();
        model.addAttribute("MoneyList", moneyList);
        return "Money_display";
    }

    @GetMapping("/editMoney/{id}")
    public String showEditMoneyForm(@PathVariable Long id, Model model) {
        Money_b money_b = moneyService.getMoney_bById(id);
        if (money_b == null) {
            throw new RuntimeException("Money details not found with id: " + id);
        }
        model.addAttribute("money_b", money_b);
        return "MoneyHistory";
    }

    @PostMapping("/editMoney/{id}")
    public String updateMoney(@PathVariable("id") Long id, @ModelAttribute("money_b") Money_b updatedMoney) {
        if (updatedMoney.getId() == null || !updatedMoney.getId().equals(id)) {
            throw new IllegalArgumentException("ID mismatch for update");
        }
        moneyService.updateMoney(updatedMoney);
        return "redirect:/money_bs";
    }

    @GetMapping("/paymentHistory")
    public String viewPaymentHistory(@RequestParam(value = "moneyId", required = false) Long moneyId, Model model) {
        List<PaymentInfo> paymentList;
        if (moneyId != null) {
            Money_b money = moneyService.getMoney_bById(moneyId);
            paymentList = paymentRepo.findByMoney(money);
        } else {
            paymentList = paymentRepo.findAll();
        }
        model.addAttribute("paymentList", paymentList);
        return "MoneyHistoryDisplay";
    }

    // Tailor Management
    // This section will be moved to TailorController
    @GetMapping("/tailorform")
    public String showTailorForm(Model model) {
        model.addAttribute("tailor", new Tailor());
        return "tailor";
    }

    @PostMapping("/SaveTailor")
    public String saveTailor(@ModelAttribute("tailor") Tailor tailor) {
        tailorService.Save_Tailor(tailor);
        return "redirect:/Dashboard";
    }

    @GetMapping("/showTailor")
    public String getTailorList(Model model) {
        List<Tailor> tailorList = tailorService.getAllTailor();
        model.addAttribute("TailorList", tailorList);
        return "showTailor";
    }

    @GetMapping("/deleteTailor/{id}")
    public String deleteTailor(@PathVariable("id") Long id) {
        tailorService.deleteTailor(id);
        return "redirect:/showTailor";
    }

    @GetMapping("/editTailor/{id}")
    public String showEditTailorForm(@PathVariable Long id, Model model) {
        Tailor tailor = tailorService.getTailorById(id);
        if (tailor == null) {
            throw new RuntimeException("Tailor not found with id: " + id);
        }
        model.addAttribute("tailor", tailor);
        return "EditTailor";
    }

    @PostMapping("/updateTailor/{id}")
    public String updateTailor(@PathVariable("id") Long id, @ModelAttribute("tailor") Tailor updatedTailor) {
        tailorService.updateTailor(updatedTailor);
        return "redirect:/showTailor";
    }
}
