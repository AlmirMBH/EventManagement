package com.zekerijah.eventdemo;

import com.zekerijah.eventdemo.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
public class IntegrationTest {
    @Autowired
    protected EventRepository eventRepository;
}
