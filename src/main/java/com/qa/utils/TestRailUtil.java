package com.qa.utils;


import com.qa.common.TestRailPropertyReader;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestRailUtil {
    public static void updateTestCase(String testCaseId, int status)  {
//        APIClient client = new APIClient(TestRailPropertyReader.getProperty("tr.endpoint").concat("index.php?/api/v2/"));
//        client.setUser(TestRailPropertyReader.getProperty("tr.username"));
//        client.setPassword(TestRailPropertyReader.getProperty("tr.password"));
//        String statusMessage = status == 1 ? "This test worked fine!" : "Test failed";
//
//        Map data = new HashMap();
//        data.put("status_id", status);
//        data.put("comment", statusMessage);
//
//        client.sendPost("add_result_for_case/" +
//                TestRailPropertyReader.getProperty("tr.runId") + "/" + testCaseId, data);
    }
}
