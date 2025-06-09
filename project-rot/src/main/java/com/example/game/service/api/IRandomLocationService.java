package com.example.game.service.api;

import com.example.game.model.Location;

/**
 * Сервис для получения случайной локации по названию.
 */
public interface IRandomLocationService {
    /**
     * Возвращает случайную локацию из списка с заданным именем.
     * 
     * @param name имя локации
     * @return случайная Location или null, если нет ни одной
     */
    Location getRandomLocationByName(String name);
}
