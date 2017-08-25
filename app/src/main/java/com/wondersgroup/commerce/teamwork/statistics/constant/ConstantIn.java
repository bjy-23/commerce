package com.wondersgroup.commerce.teamwork.statistics.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 8/24/17.
 * 列入原因
 */

public final class ConstantIn {
    /**
     * 企业列入原因
     *
     * @return
     */
    public static Map<Integer, String> QY() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "未依照《企业信息公示暂行条例》第八条规定的期限公示年度报告");
        map.put(2, "未在工商行政管理部门依照《企业信息公示暂行条例》第十条规定责令的期限内公示有关企业信息");
        map.put(3, "公示企业信息隐瞒真实情况、弄虚作假");
        map.put(4, "通过登记的住所或者经营场所无法联系");
        return map;
    }

    /**
     * 农专列入原因
     *
     * @return
     */
    public static Map<Integer, String> NZ() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "农民专业合作社未按照《农民专业合作社年度报告公示办法》规定报送年度报告并公示");
        map.put(2, "农民专业合作社年度报告信息隐瞒真实情况、弄虚作假");
        map.put(3, "工商行政管理部门在依法履职过程中通过登记的住所无法与农民专业合作社取得联系");
        return map;
    }

    /**
     * 个体列入原因
     *
     * @return
     */
    public static Map<Integer, String> GT() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "个体工商户未按照《个体工商户年度报告办法》规定报送年度报告");
        map.put(2, "个体工商户年度报告隐瞒真实情况、弄虚作假");
        map.put(3, "工商行政管理部门在依法履职过程中通过登记的经营场所及经营者住所无法与个体工商户取得联系");
        return map;
    }
}
