package com.banking.util;

import java.text.SimpleDateFormat;
import java.security.MessageDigest;
import java.util.Calendar;
import java.util.Date;

public class TokenGenerator {
    private String sentToken;

    public String generateToken() throws java.security.NoSuchAlgorithmException {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(timeStamp.getBytes());
        String stringHash = new String(messageDigest.digest());
        this.sentToken = stringHash;
        return stringHash;
    }

    public Boolean validateToken(String token) {
        //check whether 
        if(token.equals(this.sentToken)) {
            return true;
        }
        return false;
    }

    public String getTokenExpiryTimestamp(int tokenExpiryHours) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, tokenExpiryHours);
        Date expiryDate = calendar.getTime();
        return new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(expiryDate);
    }
}
