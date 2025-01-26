package api_helper;

import java.util.HashMap;
import java.util.Map;

public class FindTransactionHelper {
    public final String endpoint = "/accounts/{$accountNumber}/transactions/amount/1?timeout=30000";
    public static String accountNumber;

    public Map<String , String> setHeaders(String sessionId)
    {
        Map<String , String > headers = new HashMap<>();
        headers.put("Cookie","JSESSIONID="+sessionId);
        return headers;
    }
}
