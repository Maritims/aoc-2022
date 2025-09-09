package io.github.maritims.advent_of_code.year_one;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.stream.IntStream;

public class AccountingElf {
    @FunctionalInterface
    public interface LedgerConsideration<T> {
        boolean shouldConsider(T ledger);
    }

    static class ConsiderAllLedgers implements LedgerConsideration<JsonNode> {
        @Override
        public boolean shouldConsider(JsonNode ledger) {
            return true;
        }
    }

    static class ExcludeRedLedgers implements LedgerConsideration<JsonNode> {
        @Override
        public boolean shouldConsider(JsonNode ledger) {
            if(!ledger.isObject()) {
                return true;
            }

            var fields = ledger.fieldNames();
            while (fields.hasNext()) {
                var fieldName = fields.next();
                if("red".equalsIgnoreCase(fieldName) || (ledger.get(fieldName).isTextual() && "red".equalsIgnoreCase(ledger.get(fieldName).asText()))) {
                    return false;
                }
            }

            return true;
        }
    }

    private final LedgerConsideration<JsonNode> consideration;

    public AccountingElf(LedgerConsideration<JsonNode> consideration) {
        this.consideration = consideration;
    }

    private JsonNode readLedger(InputStream inputStream) {
        var objectMapper = new ObjectMapper();
        try {
            return objectMapper.readTree(inputStream);
        } catch (Exception e) {
            throw new RuntimeException("The elf could not read the ledger: " + e.getMessage(), e);
        }
    }

    private int balanceArray(JsonNode array) {
        return IntStream.range(0, array.size())
                .map(i -> balance(array.get(i)))
                .sum();
    }

    private int balanceObject(JsonNode object) {
        var sum    = 0;
        var fields = object.fieldNames();

        while (fields.hasNext()) {
            sum += balance(object.get(fields.next()));
        }

        return sum;
    }

    private int balance(JsonNode ledger) {
        if (ledger == null || !consideration.shouldConsider(ledger)) {
            return 0;
        }

        return switch (ledger.getNodeType()) {
            case ARRAY -> balanceArray(ledger);
            case OBJECT -> balanceObject(ledger);
            case NUMBER -> ledger.asInt();
            default -> 0;
        };
    }

    public int balance(InputStream ledgerSource) {
        var ledger = readLedger(ledgerSource);
        return balance(ledger);
    }
}
