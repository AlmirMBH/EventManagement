package com.zekerijah.eventdemo.controller.mapper;

import com.zekerijah.eventdemo.EventUtil;
import com.zekerijah.eventdemo.controller.dto.EventCreatedResDto;
import com.zekerijah.eventdemo.controller.dto.PeriodDto;
import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.domain.Period;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class EventMapperTest {

    EventMapper eventMapper = new EventMapper();
    
    @Test
    void whenValidRequest_thenMap() {
        // given
        Event generate = EventUtil.generate();

        // when
        EventCreatedResDto result = eventMapper.map(generate);

        // then
        assertThat(result).isNotNull();
        assertThat(generate.getTitle()).isEqualTo(result.getTitle());
        assertThat(generate.getDescription()).isEqualTo(result.getDescription());
        assertThat(generate.getDescription()).isEqualTo(result.getDescription());

    }

}
