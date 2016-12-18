package com.paulmhutchinson.domain.recognizer;

import com.google.gson.*;

import java.lang.reflect.Type;

public class RecognizerAdapter implements JsonDeserializer<Recognizer> {

    @Override
    public Recognizer deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject =  json.getAsJsonObject();
        RecognizerType recognizerType = RecognizerType.valueOf(jsonObject.get("recognizerType").getAsString());
        Recognizer recognizer = null;
        try {
            recognizer = (Recognizer) Class.forName(recognizerType.getRecognizerClass()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return recognizer;
    }
}
