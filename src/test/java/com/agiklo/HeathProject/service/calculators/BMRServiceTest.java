package com.agiklo.HeathProject.service.calculators;

import com.agiklo.HeathProject.model.BMR;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = {BMRService.class})
@ExtendWith(SpringExtension.class)
class BMRServiceTest {
    @Autowired
    private BMRService bMRService;

    @Test
    void shouldReturnRightCalculateResult() {
        BMR bmr = new BMR();
        bmr.setAge(0);
        bmr.setHeight(13.7);
        bmr.setBodyWeight(13.7);
        assertEquals(322.18999999999994, this.bMRService.calculate(bmr));
    }

    @Test
    void shouldReturnWrongCalculateResult() {
        BMR bmr = new BMR();
        bmr.setAge(0);
        bmr.setHeight(13.7);
        bmr.setBodyWeight(13.7);
        assertNotEquals(342342.18999999999994, this.bMRService.calculate(bmr));
    }

    @Test
    void shouldReturnRightResultForBmrCalculator() {
        BMR bmr = new BMR();
        bmr.setAge(0);
        bmr.setHeight(13.7);
        bmr.setBodyWeight(13.7);
        assertEquals(322.18999999999994, this.bMRService.bmrCalculator(bmr));
    }

    @Test
    void shouldReturnWrongResultForBmrCalculator() {
        BMR bmr = new BMR();
        bmr.setAge(0);
        bmr.setHeight(13.7);
        bmr.setBodyWeight(13.7);
        assertNotEquals(2.0, this.bMRService.bmrCalculator(bmr));
    }

    @Test
    void shouldReturnRightResultForBmrCalculator12() {
        BMR bmr = new BMR();
        bmr.setAge(0);
        bmr.setHeight(66.0);
        bmr.setBodyWeight(66.0);
        assertEquals(1560.2399999999998, this.bMRService.bmrCalculator12(bmr));
    }

    @Test
    void shouldReturnWrongResultForBmrCalculator12() {
        BMR bmr = new BMR();
        bmr.setAge(0);
        bmr.setHeight(66.0);
        bmr.setBodyWeight(66.0);
        assertNotEquals(150.23, this.bMRService.bmrCalculator12(bmr));
    }

    @Test
    void shouldReturnRightResultForBmrCalculator14() {
        BMR bmr = new BMR();
        bmr.setAge(0);
        bmr.setHeight(66.0);
        bmr.setBodyWeight(66.0);
        assertEquals(1820.2799999999995, this.bMRService.bmrCalculator14(bmr));
    }

    @Test
    void shouldReturnWrongResultForBmrCalculator14() {
        BMR bmr = new BMR();
        bmr.setAge(0);
        bmr.setHeight(66.0);
        bmr.setBodyWeight(66.0);
        assertNotEquals(20.5, this.bMRService.bmrCalculator14(bmr));
    }

    @Test
    void shouldReturnRightResultForBmrCalculator16() {
        BMR bmr = new BMR();
        bmr.setAge(0);
        bmr.setHeight(66.0);
        bmr.setBodyWeight(66.0);
        assertEquals(2080.3199999999997, this.bMRService.bmrCalculator16(bmr));
    }

    @Test
    void shouldReturnWrongResultForBmrCalculator16() {
        BMR bmr = new BMR();
        bmr.setAge(0);
        bmr.setHeight(66.0);
        bmr.setBodyWeight(66.0);
        assertNotEquals(200.319, this.bMRService.bmrCalculator16(bmr));
    }

    @Test
    void shouldReturnRightResultForBmrCalculator18() {
        BMR bmr = new BMR();
        bmr.setAge(0);
        bmr.setHeight(66.0);
        bmr.setBodyWeight(66.0);
        assertEquals(2340.3599999999997, this.bMRService.bmrCalculator18(bmr));
    }

    @Test
    void shouldReturnWrongResultForBmrCalculator18() {
        BMR bmr = new BMR();
        bmr.setAge(0);
        bmr.setHeight(66.0);
        bmr.setBodyWeight(66.0);
        assertNotEquals(20.359, this.bMRService.bmrCalculator18(bmr));
    }

    @Test
    void shouldReturnRightResultForBmrCalculator20() {
        BMR bmr = new BMR();
        bmr.setAge(0);
        bmr.setHeight(66.0);
        bmr.setBodyWeight(66.0);
        assertEquals(2600.3999999999996, this.bMRService.bmrCalculator20(bmr));
    }

    @Test
    void shouldReturnWrongResultForBmrCalculator20() {
        BMR bmr = new BMR();
        bmr.setAge(0);
        bmr.setHeight(66.0);
        bmr.setBodyWeight(66.0);
        assertNotEquals(2.39996, this.bMRService.bmrCalculator20(bmr));
    }
}

