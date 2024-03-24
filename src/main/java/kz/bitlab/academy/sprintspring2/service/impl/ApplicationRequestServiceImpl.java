package kz.bitlab.academy.sprintspring2.service.impl;


import jakarta.transaction.Transactional;
import kz.bitlab.academy.sprintspring2.entity.ApplicationRequest;
import kz.bitlab.academy.sprintspring2.repository.ApplicationRequestRepository;
import kz.bitlab.academy.sprintspring2.service.ApplicationRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationRequestServiceImpl implements ApplicationRequestService {

    private final ApplicationRequestRepository repository;

    @Transactional
    @Override
    public void create(String userName, String courseName, String commentary, String phone) {
        repository.save(new ApplicationRequest(userName, courseName, commentary, phone));
    }

    @Transactional
    @Override
    public void handle(Long id) {
        ApplicationRequest entity = findById(id);
        entity.setHandled(true);
    }

    @Transactional
    @Override
    public List<ApplicationRequest> findAll() {
        return repository.findAll();
    }

    // TODO: Реализовать
    @Transactional
    @Override
    public List<ApplicationRequest> findAllNew() {
        List<ApplicationRequest> applicationRequestsList = repository.findAll();
        return applicationRequestsList.stream().
                filter(applicationRequest -> !applicationRequest.isHandled())
                .toList();
    }

    // TODO: Реализовать
    @Transactional
    @Override
    public List<ApplicationRequest> findAllHandled() {
        List<ApplicationRequest> applicationRequestsList = repository.findAll();
        return applicationRequestsList.stream().
                filter(applicationRequest -> applicationRequest.isHandled())
                .toList();
    }

    @Transactional
    @Override
    public ApplicationRequest findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Application request not found"));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Application request not found");

        repository.deleteById(id);
    }

}

