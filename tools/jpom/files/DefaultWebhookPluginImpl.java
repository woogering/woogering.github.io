/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Code Technology Studio
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.jpom.webhook;

import cn.hutool.core.text.CharPool;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson2.JSONObject;
import io.jpom.plugin.IDefaultPlugin;
import io.jpom.plugin.PluginConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 默认到 webhook 实现
 *
 * @author bwcx_jzy
 * @since 2021/12/22
 */
@PluginConfig(name = "webhook")
@Slf4j
public class DefaultWebhookPluginImpl implements IDefaultPlugin {

    @Override
    public Object execute(Object main, Map<String, Object> parameter) {
        String webhook = StrUtil.toStringOrNull(main);
        if (StrUtil.isEmpty(webhook)) {
            return null;
        }
        try {
            log.info("webhook : " + webhook + " , parameter : " + parameter);
            HttpRequest httpRequest = HttpUtil.createPost(webhook);
            httpRequest.body(convertDingDingBody(parameter));
            try (HttpResponse execute = httpRequest.execute()) {
                String body = execute.body();
                log.info(webhook + CharPool.COLON + body);
                return body;
            }
        } catch (Exception e) {
            log.error("WebHooks 调用错误", e);
            return "WebHooks error:" + e.getMessage();
        }
    }

    private String convertDingDingBody(Map<String, Object> parameter) {
        JSONObject body = new JSONObject();
        JSONObject text = new JSONObject();
        text.put("content", translateToText(parameter));
        body.put("msgtype", "text");
        body.put("text", text.toJSONString());
        return body.toString();
    }

    private String translateToText(Map<String, Object> parameter) {
        return sorted(new ArrayList<>(parameter.keySet())).stream().filter(key -> !filter.containsKey(key)).map(key -> dictionary.getOrDefault(key, key) + " : " + parameter.get(key)).collect(Collectors.joining("\n"));
    }

    public List<String> sorted(List<String> targetList) {

        targetList.sort(((o1, o2) -> {

            int io1 = sorted.indexOf(o1);
            int io2 = sorted.indexOf(o2);

            if (io1 != -1) {
                io1 = targetList.size() - io1;
            }

            if (io2 != -1) {
                io2 = targetList.size() - io2;
            }

            return io2 - io1;

        }));

        return targetList;

    }

    private static Map<String, String> dictionary = new HashMap<>();
    private static Map<String, String> filter = new HashMap<>();
    private static List<String> sorted = new ArrayList<>();

    static {

        sorted.add("monitorName");
        sorted.add("title");
        sorted.add("content");
        sorted.add("runStatus");

        dictionary.put("nodeName", "节点");
        dictionary.put("monitorName", "监控");
        dictionary.put("projectName", "项目");
        dictionary.put("title", "标题");
        dictionary.put("content", "响应");
        dictionary.put("runStatus", "状态");

        filter.put("monitorId", "");
        filter.put("nodeId", "");
        filter.put("nodeName", "");
        filter.put("projectId", "");
        filter.put("projectName", "");

    }

}
