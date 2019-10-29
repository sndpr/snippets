package org.psc.misc.snippets;

import org.apache.commons.codec.binary.Hex;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HexStringGenerator {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstanceStrong();
        byte[] randoms32 = new byte[32];
        secureRandom.nextBytes(randoms32);
        System.out.println(Hex.encodeHexString(randoms32));

        byte[] randoms16 = new byte[16];
        secureRandom.nextBytes(randoms16);
        System.out.println(Hex.encodeHexString(randoms16));
    }

}
