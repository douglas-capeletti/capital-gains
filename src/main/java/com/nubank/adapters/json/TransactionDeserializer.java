package com.nubank.adapters.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.nubank.domain.Transaction;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class TransactionDeserializer extends StdDeserializer<Transaction> {

    public TransactionDeserializer() {
        this(null);
    }

    public TransactionDeserializer(Class<?> transaction) {
        super(transaction);
    }

    @Override
    public Transaction deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {
        ObjectCodec codec = parser.getCodec();
        JsonNode node = codec.readTree(parser);
        String type = node.get("operation").asText();
        var bigDecimal = BigDecimal.valueOf(node.get("unit-cost").asDouble()).setScale(2, RoundingMode.HALF_UP);
        Double unitCost = bigDecimal.doubleValue();
        Long quantity = node.get("quantity").asLong();
        return new Transaction(type, unitCost, quantity);
    }
}
