package org.example.exam24hours.service;

import org.example.exam24hours.model.SensorReading;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DefaultEpicenterEstimator implements EpicenterEstimator {

    @Override
    public double[] estimateEpicenter(List<SensorReading> readings) {

        return new double[]{0, 0};

    }
}