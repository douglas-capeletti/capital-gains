package com.nubank.adapters.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.nubank.adapters.ObjectParser;
import com.nubank.domain.ResultTax;
import com.nubank.domain.Transaction;

import java.util.List;

public class JacksonAdapter implements ObjectParser {

    private final ObjectMapper mapper;

    public JacksonAdapter() {
        SimpleModule module = new SimpleModule("CustomParsers", new Version(1, 0, 0, null, null, null));
        module.addDeserializer(Transaction.class, new TransactionDeserializer());
        module.addSerializer(ResultTax.class, new ResultSerializer());
        mapper = new ObjectMapper();
        mapper.registerModule(module);
    }

    @Override
    public <T> List<T> stringToObjectList(String src, Class<T> clazz) {
        try {
            return mapper.readerForListOf(clazz).readValue(src);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> String objectListToString(List<T> src) {
        try {
            return mapper.writeValueAsString(src);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
