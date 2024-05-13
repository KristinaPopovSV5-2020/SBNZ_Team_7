package com.ftn.sbnz.model;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.List;

public class ObjectIdListSerializer extends JsonSerializer<List<ObjectId>> {
    @Override
    public void serialize(List<ObjectId> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        if (value != null) {
            for (ObjectId id : value) {
                if (id != null) {
                    gen.writeString(id.toHexString());
                } else {
                    gen.writeNull();
                }
            }
        }
        gen.writeEndArray();
    }
}