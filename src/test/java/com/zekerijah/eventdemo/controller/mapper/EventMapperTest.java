package com.zekerijah.eventdemo.controller.mapper;

import com.zekerijah.eventdemo.EventUtil;
import com.zekerijah.eventdemo.controller.dto.CreateEventRes;
import com.zekerijah.eventdemo.domain.Event;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
public class EventMapperTest {

    EventMapper eventMapper = new EventMapper();
    PeriodMapper periodMapper = new PeriodMapper();

    @Test
    void whenValidRequest_thenMap() {
        // given
        Event generate = EventUtil.generate();
        // when
        CreateEventRes result = eventMapper.map(generate);
        // then
        assertThat(result).isNotNull();
        assertThat(generate.getId()).isEqualTo(result.getId());
        assertThat(generate.getTitle()).isEqualTo(result.getTitle());
        assertThat(generate.getDescription()).isEqualTo(result.getDescription());
        assertThat(generate.getDescription()).isEqualTo(result.getDescription());
        assertThat(periodMapper.map(generate.getPeriod())).isEqualTo(result.getPeriod());

    }

}
