package com.paulmhutchinson.domain.recognizer;

import com.google.gson.*;

import java.lang.reflect.Type;

public class RecognizerAdapter implements JsonDeserializer<Recognizer> {

    @Override
    public Recognizer deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject =  json.getAsJsonObject();
        RecognizerType recognizerType = RecognizerType.valueOf(jsonObject.get("recognizerType").getAsString());
        try {
            return (Recognizer) Class.forName(recognizerType.getClazz().getName()).newInstance();
        } catch (ReflectiveOperationException e) {
            throw new InvalidRecognizerRequestException(e.getMessage());
        }
    }

    private static final class InvalidRecognizerRequestException extends RuntimeException {
        public InvalidRecognizerRequestException(String message) {
            super(message);
        }
    }
}
