package org.example.exam24hours.service;

import org.example.exam24hours.model.SensorReading;

import java.util.List;

public interface EpicenterEstimator {
    double[] estimateEpicenter(List<SensorReading> readings);
}
