package com.jiangrongxin.train.regular;

import java.util.List;
import java.util.Map;

/**
 * 测试正则表达式的效果
 *
 * @author Helay
 * @date 2020/5/19 18:21
 */
public class AddressTest {
    public static void main(String[] args) {
        String[] addressList = new String[]{"中华人民共和国吉林省长春市二道区临河街万兴小区4栋2门", "中华人民共和国河北省石家庄市武林区砂石街河东小区8栋1单元"};
        for (String address : addressList) {
            List<Map<String, String>> list = Address.extractAddress(address);
            System.out.println(list);
        }
    }
}
