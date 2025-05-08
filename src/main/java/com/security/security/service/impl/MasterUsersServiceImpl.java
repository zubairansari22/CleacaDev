package com.security.security.service.impl;

import com.security.security.entity.MasterUsers;
import com.security.security.records.RequestRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MasterUsersServiceImpl implements MasterUsersService {

//    @Autowired
//    MasterUsersRepository masterUsersRepository;

    @Override
    public Object saveUser(RequestRecord.RegisterRecord request) {
        MasterUsers user = new MasterUsers();
        user.setUserName(request.name());
        user.setUserEmail(request.email());
        user.setPhoneNumber(request.phoneNo());
        user.setPassword("GauravGandu");
//        masterUsersRepository.save(user);
        return "Success";
    }




}
