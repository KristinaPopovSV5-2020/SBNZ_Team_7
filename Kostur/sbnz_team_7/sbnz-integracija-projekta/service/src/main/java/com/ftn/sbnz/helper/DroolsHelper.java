package com.ftn.sbnz.helper;

import org.bson.types.ObjectId;

public class DroolsHelper {
    public static ObjectId toObjectId(String id) {
        return new ObjectId(id);
    }
}