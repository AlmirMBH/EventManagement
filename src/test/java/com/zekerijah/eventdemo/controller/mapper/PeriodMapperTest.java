package com.zekerijah.eventdemo.controller.mapper;

import com.zekerijah.eventdemo.PeriodUtil;
import com.zekerijah.eventdemo.controller.dto.PeriodDto;
import com.zekerijah.eventdemo.domain.Period;
import org.junit.jupiter.api.Test;

import java.sql.Time;

import static org.assertj.core.api.Assertions.assertThat;

public class PeriodMapperTest {

    PeriodMapper periodMapper = new PeriodMapper();

    @Test
    void whenValidTime_thenMap(){
        //given
        Period generate = PeriodUtil.generate();
        //when
        PeriodDto result = periodMapper.map(generate);
        //then
        assertThat(result).isNotNull();
        assertThat(generate.getStartDate()).isEqualTo(result.getStart().toLocalDate());
        assertThat(generate.getStartTime()).isEqualTo(Time.valueOf(result.getStart().toLocalTime()));
        assertThat(generate.getEndDate()).isEqualTo(result.getEnd().toLocalDate());
        assertThat(generate.getEndTime()).isEqualTo(Time.valueOf(result.getEnd().toLocalTime()));
    }
}
