package com.domain.main.util;


import com.domain.main.app.Constant;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by cloud_wang on 15/12/23.
 */
public class MD5Encryption {

    /**
     * @param params 参数集合
     * @return 32位密文
     */
    public static String getSecretKey(HashMap<String, String> params, String time) {
        String paramsStr = "";
        params.put("time", time);
        params.put("key", Constant.SECRET_KEY);
        params.put("mark", Constant.PHONE_MARK);
        ArrayList<String> keyList = new ArrayList();
        Iterator iter = params.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            keyList.add(entry.getKey() + "");
        }
        Collections.sort(keyList);
        for (int i = 0; i < keyList.size(); i++) {
            paramsStr = paramsStr + params.get(keyList.get(i));
        }
        return encryption(paramsStr);
    }

    /**
     * @param plainText 参数Str
     */
    public static String encryption(String plainText) {

        String md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();

            int i;

            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            md5 = buf.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }
}
