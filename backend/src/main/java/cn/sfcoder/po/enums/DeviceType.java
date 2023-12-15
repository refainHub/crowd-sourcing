package cn.sfcoder.po.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Data;
import lombok.Getter;

/**
 * @Author: refain
 * @Description:
 * @Date: 2023/12/15 19:39
 * @Version: 1.0
 */
@Getter
public enum DeviceType {
    Windows(0,"Windows"),Linux(1,"Linux"),MacOS(2,"MacOS"),
    Android(3,"Android"),IOS(4,"ios"),HarmonyOS(5,"HarmonyOS");

    public static String[] getSuffix(DeviceType device){
        switch (device){
            case Windows:return new String[]{"exe","zip"};
            case MacOS:return new String[]{"dmg","zip"};
            case Android:return new String[]{"apk","zip"};
            case IOS:return new String[]{"ipa","zip"};
            case HarmonyOS:return new String[]{"hap","zip"};
            default:return new String[]{"zip"};
        }
    }


    @EnumValue
    private final int code;

    @JsonValue
    private final String name;

    DeviceType(int code, String name) {
        this.code = code;
        this.name = name;
    }


}