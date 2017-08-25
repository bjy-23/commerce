package com.wondersgroup.commerce.teamwork.statistics.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 8/24/17.
 * 移出原因
 */

public final class ConstantOut {
    /**
     * 企业移出原因
     *
     * @return
     */
    public static Map<Integer, String> QY() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "列入经营异常名录3年内且依照《经营异常名录管理办法》第六条规定被列入经营异常名录的企业，可以在补报未报年份的年度报告并公示后，申请移出");
        map.put(2, "列入经营异常名录3年内且依照《经营异常名录管理办法》第七条规定被列入经营异常名录的企业履行公示义务后，申请移出");
        map.put(3, "列入经营异常名录3年内且依照《经营异常名录管理办法》第八条规定被列入经营异常名录的企业更正其公示的信息后，申请移出");
        map.put(4, "列入经营异常名录3年内且依照《经营异常名录管理办法》第九条规定被列入经营异常名录的企业，依法办理住所或者经营场所变更登记后，申请移出");
        map.put(5, "列入经营异常名录3年内且依照《经营异常名录管理办法》第九条规定被列入经营异常名录的企业，提出通过登记的住所或者经营场所可以重新取得联系，申请移出");
        map.put(6, "列入经营异常名录届满3年仍未履行公示义务的，列入严重违法企业名单，自动移出");
        map.put(7, "企业注销，自动移出");
        return map;
    }

    /**
     * 农专移出原因
     *
     * @return
     */
    public static Map<Integer, String> NZ() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "依照《农民专业合作社年度报告公示办法》第十条规定被列入经营异常名录的农民专业合作社，可以在补报未报年份的年度报告并公示后，申请移出");
        map.put(2, "依照《农民专业合作社年度报告公示办法》第十一条规定被列入经营异常名录的农民专业合作社，更正其公示的年度报告信息后，申请移出");
        map.put(3, "依照《农民专业合作社年度报告公示办法》第十二条规定被列入经营异常名录的农民专业合作社，依法办理住所变更登记，申请移出");
        map.put(4, "依照《农民专业合作社年度报告公示办法》第十二条规定被列入经营异常名录的农民专业合作社，提出通过登记的住所可以重新取得联系，申请移出");
        return map;
    }

    /**
     * 个体移出原因
     *
     * @return
     */
    public static Map<Integer, String> GT() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "依照《个体工商户年度报告办法》第十三条规定被标记为经营异常状态的个体工商户，向工商行政管理部门补报纸质年度报告并申请恢复正常记载状态");
        map.put(2, "依照《个体工商户年度报告办法》第十四条规定被标记为经营异常状态的个体工商户，向工商行政管理部门报送更正后的纸质年度报告并申请恢复正常记载状态");
        map.put(3, "依照《个体工商户年度报告办法》第十五条规定被标记为经营异常状态的个体工商户，依法办理经营场所、经营者住所变更登记，申请恢复正常记载状态");
        map.put(4, "依照《个体工商户年度报告办法》第十五条规定被标记为经营异常状态的个体工商户，提出通过登记的经营场所或者经营者住所可以重新取得联系，申请恢复正常记载状态");
        return map;
    }
}
