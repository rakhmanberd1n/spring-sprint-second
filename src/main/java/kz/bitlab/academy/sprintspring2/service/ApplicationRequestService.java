package kz.bitlab.academy.sprintspring2.service;

import kz.bitlab.academy.sprintspring2.entity.ApplicationRequest;

import java.util.List;

public interface ApplicationRequestService {

    void create(String userName, String courseName, String commentary, String phone);

    void handle(Long id);

    List<ApplicationRequest> findAll();

    List<ApplicationRequest> findAllNew();

    List<ApplicationRequest> findAllHandled();

    ApplicationRequest findById(Long id);

    void delete(Long id);

}
