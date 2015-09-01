package com.wx.demo.test;

import java.math.BigInteger;

/** 
 * @author browserwang 
 * @version 2014年10月23日 下午12:02:36 
 * 类说明 
 */
public class QcallUinUtils {

	public static String longToString(long ul) {
        BigInteger big = BigInteger.valueOf(ul);
        if (big.signum() < 0) {
            big = big.add(BigInteger.ONE.shiftLeft(64));
        }
        return big.toString();
    }

    public static long stringToLong(String big) {
        BigInteger bigInt = new BigInteger(big);
        return bigInt.longValue();
    }
    
    public static long string2Long(String number) {
        try {
            return Long.parseLong(number);
        } catch (Exception e) {
            return 0;
        }
    }
}
