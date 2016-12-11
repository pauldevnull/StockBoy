package com.paulmhutchinson.domain.recognizer;

import com.google.gson.*;
import com.paulmhutchinson.domain.recognizer.pattern.flag.highandtight.HighAndTightFlag;

import java.lang.reflect.Type;

public class RecognizerAdapter implements JsonDeserializer<Recognizer> {

    @Override
    public Recognizer deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject =  json.getAsJsonObject();
        RecognizerType recognizerType = RecognizerType.valueOf(jsonObject.get("recognizerType").getAsString());

        if (recognizerType == RecognizerType.HIGH_AND_TIGHT_FLAG) {
            return new HighAndTightFlag();
        } else {
            return null;
        }
    }
}
