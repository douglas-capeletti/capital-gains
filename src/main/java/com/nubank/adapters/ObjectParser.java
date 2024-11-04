package com.nubank.adapters;

import java.util.List;

public interface ObjectParser {

    <X> List<X> stringToObjectList(String src, Class<X> clazz);
    <Y> String objectListToString(List<Y> clazz);

}
