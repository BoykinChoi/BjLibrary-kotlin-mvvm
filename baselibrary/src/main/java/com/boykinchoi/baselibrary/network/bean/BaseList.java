package com.boykinchoi.baselibrary.network.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * list，T这样的数据
 * {
 * msg:"",
 * code:"200",
 * data:"
 * {
 * total:20,
 * list:
 * { ... }
 * }"
 * }
 *
 * 题库-获取同步目录列表数据
 * {
 * msg:"",
 * code:"200",
 * data:"
 * {
 * Count:20,
 * Result:
 * { ... }
 * }"
 * }
 *
 * @param <E>
 */
public class BaseList<E> {
    /**
     * 总的资源数量
     */
    @SerializedName(value = "total", alternate = "Count")
    public int total;

    @SerializedName(value = "list", alternate = "Result")
    public List<E> list;

    public boolean isEmptyList() {
        return list == null || list.size() == 0;
    }
}
