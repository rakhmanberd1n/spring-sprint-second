package kz.bitlab.academy.sprintspring2.controller;

import kz.bitlab.academy.sprintspring2.service.ApplicationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ApplicationRequestController {

    private final ApplicationRequestService service;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("requests", service.findAll());
        return "index";
    }

    @GetMapping("/add")
    public String addRequestPage() {
        return "addRequest";
    }

    @PostMapping("/add")
    public String create(@RequestParam("userName") String userName,
                         @RequestParam("courseName") String courseName,
                         @RequestParam("phone") String phone,
                         @RequestParam("commentary") String commentary) {

        if (userName.isBlank() || courseName.isBlank() || phone.isBlank())
            return "redirect:/add?badRequest";

        service.create(userName, courseName, commentary, phone);

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String detailsPage(@PathVariable Long id, Model model) {
        model.addAttribute("request", service.findById(id));

        return "details";
    }

    @PostMapping("/{id}")
    public String handle(@PathVariable Long id) {
        service.handle(id);

        return "redirect:/" + id;
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);

        return "redirect:/";
    }

    @GetMapping("/newRequest")
    public String newRequest(Model model) {
        model.addAttribute("newReq", service.findAllNew());
        return "/newRequest";
    }

    @GetMapping("/handledRequest")
    public String handledRequest(Model model) {
        model.addAttribute("handled", service.findAllHandled());
        return "handledRequest";
    }

}
