package com.zekerijah.eventdemo;

import com.zekerijah.eventdemo.domain.Event;
import com.zekerijah.eventdemo.domain.Period;

public class EventUtil {

    public static Event generate(){

        Period period = PeriodUtil.generate();

        return Event.builder()
                .title("Dummy Title")
                .description("Dummy Description")
                .period(period)
                .build();
    }


}
