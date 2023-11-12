package com.sehee.heesinsa.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public class JdbcUtil {
    public static final int EXPECTED_UPDATE_COUNT = 1;

    public static UUID toUUID(byte[] bytes) {
        var byteBuffer = ByteBuffer.wrap(bytes);
        return new UUID(byteBuffer.getLong(), byteBuffer.getLong());
    }

}
