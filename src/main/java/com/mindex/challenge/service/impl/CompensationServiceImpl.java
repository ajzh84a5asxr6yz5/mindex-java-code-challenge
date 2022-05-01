package com.mindex.challenge.service.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService{

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    @Autowired
    private CompensationRepository compensationRepository;

    @Override
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public Compensation create(Compensation compensation){
        LOG.debug("Saving compensation [{}]", compensation);
        return compensationRepository.save(compensation);
    }

    @Override
    public Compensation read(String id) {
        LOG.debug("Reading compensation with employee id [{}]", id);

        Compensation compensation = compensationRepository.findByEmployee_EmployeeId(id);

        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return compensation;
    }

}
