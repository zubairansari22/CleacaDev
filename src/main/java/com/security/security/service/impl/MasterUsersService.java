package com.security.security.service.impl;

import com.security.security.records.RequestRecord;
import org.springframework.stereotype.Service;

@Service
public interface MasterUsersService {
    Object saveUser(RequestRecord.RegisterRecord request);
}
