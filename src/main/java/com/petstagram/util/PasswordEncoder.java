package com.petstagram.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

/**
 * 비밀번호 암호화 및 비교를 위한 유틸리티 클래스.
 * BCrypt를 사용하여 비밀번호를 암호화하고, 암호화된 비밀번호와 비교하는 기능을 제공합니다.
 */
public class PasswordEncoder {

    /**
     * 주어진 원본 비밀번호를 암호화하여 반환하는 메서드.
     * @param rawPassword 원본 비밀번호
     * @return 암호화된 비밀번호
     */
    public static String encode(String rawPassword) {
        // 최소 비용을 사용하여 비밀번호 암호화
        return BCrypt.withDefaults().hashToString(BCrypt.MIN_COST, rawPassword.toCharArray());
    }

    /**
     * 원본 비밀번호와 암호화된 비밀번호를 비교하는 메서드.
     * @param rawPassword 원본 비밀번호
     * @param encodedPassword 암호화된 비밀번호
     * @return 비밀번호가 일치하면 true, 그렇지 않으면 false
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        // BCrypt를 사용하여 비밀번호 비교
        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), encodedPassword);
        return result.verified;
    }
}
