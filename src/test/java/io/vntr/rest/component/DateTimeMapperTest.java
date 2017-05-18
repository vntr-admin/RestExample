package io.vntr.rest.component;

import io.vntr.rest.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;

/**
 * Created by robertlindquist on 5/17/17.
 */
public class DateTimeMapperTest {
    private DateTimeMapper dateTimeMapper;

    @Before
    public void init() {
        dateTimeMapper = new DateTimeMapper();
    }

    @Test
    public void testFromEntity() {
        Date date = TestUtils.getDateChicago(2017, 1, 15, 19, 30);

        DateTimeDTO expectedResult = new DateTimeDTO("01/15/2017", "07:30 PM", "2017-01-15T19:30");
        DateTimeDTO result = dateTimeMapper.fromEntity(date);
        assertEquals(expectedResult, result);
    }
}
