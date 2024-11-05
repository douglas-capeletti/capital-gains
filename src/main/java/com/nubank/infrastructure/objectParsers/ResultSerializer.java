package com.nubank.infrastructure.objectParsers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.nubank.domain.ResultTax;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ResultSerializer extends StdSerializer<ResultTax> {

    public ResultSerializer() {
        this(null);
    }

    public ResultSerializer(Class<ResultTax> result) {
        super(result);
    }

    @Override
    public void serialize(ResultTax resultTax, JsonGenerator jsonGenerator, SerializerProvider serializer) throws IOException {
        jsonGenerator.writeStartObject();
        var bigDecimal = BigDecimal.valueOf(resultTax.getTax()).setScale(2, RoundingMode.HALF_UP);
        jsonGenerator.writeNumberField("tax", bigDecimal);
        jsonGenerator.writeEndObject();
    }
}
