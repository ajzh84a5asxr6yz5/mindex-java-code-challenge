package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public ReportingStructure read(String id) {
        LOG.debug("Getting reporting structure for [{}]", id);

        Employee root = employeeRepository.findByEmployeeId(id);

        if (root == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return getReportingStructure(root, 0);
    }

    private ReportingStructure getReportingStructure(Employee root, int count){

        //base case
        if(root == null || root.getDirectReports() == null || root.getDirectReports().size() == 0){
            return new ReportingStructure(root, count);
        }

        //The Employees in directReports do not have their fields eagerly fetched, so fetch/set them now
        root.getDirectReports().forEach(x -> x.setAllFields(employeeRepository.findByEmployeeId(x.getEmployeeId())));

        //Count the direct reports in the hierarchy beneath the Employee
        for(Employee directReport : root.getDirectReports()){
            count += getReportingStructure(directReport, count).getNumberOfReports();
        }

        return new ReportingStructure(root, count + root.getDirectReports().size());

    }

}
